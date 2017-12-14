package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.model.Activity;
import com.bestv.wechat.liteapp.premierleague.model.Comment;
import com.bestv.wechat.liteapp.premierleague.service.impl.ActivityService;
import com.bestv.wechat.liteapp.premierleague.utility.AppConfigurationUtil;
import com.bestv.wechat.liteapp.premierleague.utility.SessionUtil;
import com.bestv.wechat.liteapp.premierleague.utility.StringUtils;
import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;
import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/premierleague")
public class ActivityController {

    private Logger logger = LogManager.getLogger(ActivityController.class);

    @Autowired
    ActivityService activityService;

    // 查询评论
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, timeout = 15)
    void fetchActivity(HttpServletRequest request, HttpServletResponse response) {
        // 返回值相关
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();
        String strSessionId="";
        String strSessionContent ="";
        try {
            // 获取请求参数
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject joPostData = JSONObject.parseObject(sb.toString());

            strSessionId = joPostData.getString("session_id");

            HttpSession session = request.getSession();
            //strSessionId = session.getId();
            //session.setAttribute(strSessionId, "mQP0MMi16UWrKHTgPCa5VA==+o23QP0eenOFaSrCsx1rkPxTSWx_8");
            strSessionContent = String.valueOf(session.getAttribute(strSessionId));
            if (StringUtils.hasText(strSessionContent)) {

                Activity activity=new Activity();
                activity=activityService.fetchActivity();
                String[] strArray = String.valueOf(strSessionContent).split("\\|\\|");
                String strOpenId = strArray[1];
                String StrPageUrlWithoutParam=activity.getStrPageUrl();
                StringBuffer sbPageUrl=new StringBuffer(StrPageUrlWithoutParam);
                sbPageUrl.append("?id=").append(strOpenId);
                String StrPageUrl=sbPageUrl.toString();
                JSONObject joData=new JSONObject();
                joData.put("page_url",StrPageUrl);
                jaDatas.add(joData);

                mResponse.put("business_code", "successful");
                mResponse.put("description", "获取活动地址成功");
                mResponse.put("datas", jaDatas);
                mResponse.put("page_info", mPageInfo);
                joRsponse = JSONUtils.toJSONString(mResponse);

            } else {
                mResponse.put("business_code", "failure");
                mResponse.put("description", "session_id过期");
                mResponse.put("datas", jaDatas);
                mResponse.put("page_info", mPageInfo);
                joRsponse = JSONUtils.toJSONString(mResponse);
            }
        } catch (Exception e) {
            JSONObject jo = new JSONObject();
            jo.put("Exception", e.toString());
            jaDatas.add(jo);
            mResponse.put("business_code", "failure");
            mResponse.put("description", "获取活动地址出错");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            logger.error("获取活动地址出错：", e);
            // TODO Auto-generated catch block
            e.printStackTrace();
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

}
