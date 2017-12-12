package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.function.SinaTimelineFunction;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.model.SinaUser;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaTimelineService;
import com.bestv.wechat.liteapp.premierleague.utility.JSONUtil;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/premierleague")
public class SinaTimelineController {

    private Logger logger = LogManager.getLogger(SinaAuthController.class);

    @Autowired
    SinaTimelineService sinaTimeLineService;

    @RequestMapping(value = "/timeline/list", method = RequestMethod.GET)
    void getSinaTimeLine(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = null;

        int iPageNum = Integer.parseInt(request.getParameter("page"));
        int iPageSize = Integer.parseInt(request.getParameter("count"));
        long iRange = Long.parseLong(request.getParameter("range"));
        Map<String, Object> mResponse = new HashMap<String, Object>();
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();
        PageInfo pSinaTimeline;
        String joRsponse = "";
        try {
            if (iRange == 0) {
                pSinaTimeline = sinaTimeLineService.fetchSinaTimeline(iPageNum, iPageSize);
            } else {
                pSinaTimeline = sinaTimeLineService.fetchSinaTimeline(iPageNum, iPageSize, iRange);
            }
            int iCount = pSinaTimeline.getSize();
            long lTotal = pSinaTimeline.getTotal();
            int iPages = pSinaTimeline.getPages();
            Boolean bIsFirstPage = pSinaTimeline.isIsFirstPage();
            Boolean bIsLastPage = pSinaTimeline.isIsLastPage();
            Boolean bHasPreviousPag = pSinaTimeline.isHasPreviousPage();
            Boolean bHasNextPage = pSinaTimeline.isHasNextPage();

            mPageInfo.put("count", iCount);
            mPageInfo.put("total", lTotal);
            mPageInfo.put("pages", iPages);
            mPageInfo.put("is_first_page", bIsFirstPage);
            mPageInfo.put("is_last_page", bIsLastPage);
            mPageInfo.put("has_previous_page", bHasPreviousPag);
            mPageInfo.put("has_next_page", bHasNextPage);
            List<SinaTimeline> liSinaTimeline = pSinaTimeline.getList();

            for (SinaTimeline sinaTimeline : liSinaTimeline) {
                Map<String, Object> mSinaTimeline = new HashMap<String, Object>();
                Map<String, Object> mSinaUser = new HashMap<String, Object>();

                //微博作者信息
                SinaUser sinaUser = sinaTimeline.getSinaUser();
                mSinaUser.put("id", sinaUser.getStrId());
                mSinaUser.put("screen_name", sinaUser.getStrScreenName());
                mSinaUser.put("profile_image_url", sinaUser.getStrProfileImageUrl());
                mSinaUser.put("avatar_large", sinaUser.getStrAvatarLarge());
                mSinaUser.put("avatar_hd", sinaUser.getStrAvatarHd());
                mSinaUser.put("followers_count", sinaUser.getiFollowersCount());
                mSinaUser.put("friends_count", sinaUser.getiFriendsCount());

                //微博图片列表
                JSONArray jaPicUrls = JSONArray.parseArray(sinaTimeline.getStrPicUrls());

                //微博
                mSinaTimeline.put("id", sinaTimeline.getlId());
                mSinaTimeline.put("text", sinaTimeline.getStrText());
                mSinaTimeline.put("created_at", sinaTimeline.gettCreatedAt());
                mSinaTimeline.put("source", sinaTimeline.getStrSource());
                mSinaTimeline.put("reposts_count", sinaTimeline.getiRepostsCount());
                mSinaTimeline.put("comments_count", sinaTimeline.getiCommentsCount());
                mSinaTimeline.put("attitudes_count", sinaTimeline.getiAttitudesCount());
                mSinaTimeline.put("user", mSinaUser);
                mSinaTimeline.put("pic_urls", jaPicUrls);
                jaDatas.add(mSinaTimeline);
            }
            mResponse.put("business_code", "successful");
            mResponse.put("description", "");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
        } catch (Exception e) {
            mResponse.put("business_code", "failure");
            mResponse.put("description", e.toString());
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            logger.error("微博查询失败", e);
        } finally {
            try {
                out = response.getWriter();
                out.write(joRsponse);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    SinaTimelineFunction sinaTimeLineFunction;

    @RequestMapping(value = "/timeline/pull", method = RequestMethod.GET)
    void pullSinaTimeLine(HttpServletRequest request, HttpServletResponse response) {
        sinaTimeLineFunction.pullTimelineFromSina();
    }
}
