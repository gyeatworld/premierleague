package com.bestv.wechat.liteapp.premierleague.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimelinePic;
import com.bestv.wechat.liteapp.premierleague.model.SinaUser;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaAuthService;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaTimelinePicService;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaTimelineService;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaUserService;
import com.bestv.wechat.liteapp.premierleague.utility.AppConfigurationUtil;
import com.bestv.wechat.liteapp.premierleague.utility.DateUtils;
import com.bestv.wechat.liteapp.premierleague.utility.JSONUtil;
import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;

import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SinaTimelineFunction {

    @Autowired
    SinaAuthService sinaAuthService;
    @Autowired
    SinaTimelineService sinaTimelineService;
    @Autowired
    SinaTimelinePicService sinaTimelinePicService;
    @Autowired
    SinaUserService sinaUserService;

    private static String SINA_TIME_LINE_URL = AppConfigurationUtil.get("sina.timeline.url");

    public void pullTimelineFromSina() {
        //获取Access Token
        //SinaAuthAccessToken sinaAuthAccessToken=sinaAuthService.fetchSinaAuthAccessToken();
        //String strAccessToken=sinaAuthAccessToken.getStrAccessToken();
        String strAccessToken = "2.00gPHyGCGZPleDc44e41c29fwGX5MB";
        String strPrarm = "access_token=" + strAccessToken+"&count=150";
        //获取博文
        String strResult = WebUtils.sendGet(SINA_TIME_LINE_URL, strPrarm);
        JSONObject joResult = JSONObject.parseObject(strResult);
        JSONArray jaTimeline = JSONArray.parseArray(joResult.getString("statuses"));



        List<SinaTimeline> liSinaTimeline = new ArrayList<SinaTimeline>();
        //List<SinaTimelinePic> liSinaTimelinePic = new ArrayList<SinaTimelinePic>();
        List<SinaUser> liSinaUser = new ArrayList<SinaUser>();

        //分析、打包数据
        for (Object oSinaTimeline : jaTimeline) {

            SinaTimeline sinaTimeline = new SinaTimeline();
            SinaUser sinaUser = new SinaUser();

            JSONObject joTimeline = JSONObject.parseObject(String.valueOf(oSinaTimeline));

            //获取用户、获取图片
            JSONObject joSinaUser = JSONObject.parseObject(joTimeline.getString("user"));
            JSONArray jaSinaPic = JSONArray.parseArray(joTimeline.getString("pic_urls"));
            JSONArray jaPicUrls=new JSONArray();
            //生成中图和大图
            Iterator iteratorPic = jaSinaPic.iterator();
            while(iteratorPic.hasNext()){
                JSONObject joSinaPic=(JSONObject)iteratorPic.next();
                String strThumbnailPicUrl=joSinaPic.getString("thumbnail_pic");
                joSinaPic.put("middle_pic",strThumbnailPicUrl.replace("thumbnail","bmiddle"));
                joSinaPic.put("large_pic",strThumbnailPicUrl.replace("thumbnail","large"));
                jaPicUrls.add(joSinaPic);
            }

            long lId=Long.parseLong(joTimeline.getString("idstr"));
            String strText= Base64.encodeBase64String(joTimeline.getString("text").getBytes());
            String strCreateAt=joTimeline.getString("created_at");
            String strRepostsCount=joTimeline.getString("reposts_count");
            String strCommentsCount=joTimeline.getString("comments_count");
            String strAttitudesCount=joTimeline.getString("attitudes_count");
            String strUserId=joSinaUser.getString("id");
            /****************封装微博博文******************/
            //如果包含视频，则放弃该条微博
            //if(strText.contains("http://")){
            //    continue;
            //}
            sinaTimeline.setStrText(strText);
            sinaTimeline.setlId(lId);
            //把String类型的格林威治时间转换成java.sql.Timestamp
            Date dCreatedAt=DateUtils.praseGMTStringToDate(strCreateAt);
            Timestamp tCreatedAt=DateUtils.praseDateToTimestamp(dCreatedAt);
            sinaTimeline.settCreatedAt(tCreatedAt);
            //此处故意写死
            sinaTimeline.setStrSource("新浪微博");
            sinaTimeline.setiRepostsCount(Integer.parseInt(strRepostsCount));
            sinaTimeline.setiCommentsCount(Integer.parseInt(strCommentsCount));
            sinaTimeline.setiAttitudesCount(Integer.parseInt(strAttitudesCount));
            sinaTimeline.setStrSinaUserId(strUserId);
            sinaTimeline.setStrPicUrls(jaPicUrls.toString());
            liSinaTimeline.add(sinaTimeline);

            /****************封装微博用户******************/

            //过滤List，去除重复
            Iterator iteratorUser = liSinaUser.iterator();
            List<String> liUserIds=new ArrayList<String>();
            while(iteratorUser.hasNext()){
                SinaUser tempSinaUser=(SinaUser)iteratorUser.next();
                if(!liUserIds.contains(tempSinaUser.getStrId())) {
                    liUserIds.add(tempSinaUser.getStrId());
                }
            }
            if(!liUserIds.contains(strUserId)){
                sinaUser.setStrId(strUserId);
                sinaUser.setStrScreenName(joSinaUser.getString("screen_name"));
                sinaUser.setStrDescription(joSinaUser.getString("description"));
                sinaUser.setStrProfileImageUrl(joSinaUser.getString("profile_image_url"));
                sinaUser.setStrAvatarLarge(joSinaUser.getString("avatar_large"));
                sinaUser.setStrAvatarHd(joSinaUser.getString("avatar_hd"));
                sinaUser.setStrUrl(joSinaUser.getString("url"));//博客地址
                sinaUser.setStrProfileUrl(joSinaUser.getString("profile_url"));
                sinaUser.setiFollowersCount(Integer.parseInt(joSinaUser.getString("followers_count")));//粉丝数
                sinaUser.setiFriendsCount(Integer.parseInt(joSinaUser.getString("friends_count")));//关注数
                sinaUser.setiStatusesCount(Integer.parseInt(joSinaUser.getString("statuses_count")));//微博数
                sinaUser.setiFavouritesCount(Integer.parseInt(joSinaUser.getString("favourites_count")));//收藏数
                liSinaUser.add(sinaUser);
            }

            /****************封装微博图片******************/
            /*
            Iterator iteratorPic = jaSinaPic.iterator();
            while(iteratorPic.hasNext()){
                JSONObject joSinaPic=(JSONObject)iteratorPic.next();
                SinaTimelinePic sinaTimelinePic = new SinaTimelinePic();
                String strThumbnailPicUrl=joSinaPic.getString("thumbnail_pic");
                sinaTimelinePic.setStrThumbnailPic(strThumbnailPicUrl);
                sinaTimelinePic.setStrMiddlePic(strThumbnailPicUrl.replace("thumbnail","bmiddle"));
                sinaTimelinePic.setStrOriginalPic(strThumbnailPicUrl.replace("thumbnail","large"));
                sinaTimelinePic.setStrSinaTimelineId(strId);
                liSinaTimelinePic.add(sinaTimelinePic);
            }*/

        }


        //保存微博内容
        saveSinaTimeline(liSinaTimeline);
        //保存微博用户
        saveSinaUser(liSinaUser);
        //保存微博图片
        //saveSinaTimelinePic(liSinaTimelinePic);

    }

    public PageInfo getTimeline(int iPageNum, int iPageSize){
        return sinaTimelineService.fetchSinaTimeline(iPageNum,iPageSize);
    }
    public void saveSinaUser(List<SinaUser> liSinaUser){
        sinaUserService.insertSinaUser(liSinaUser);
    }
    public void saveSinaTimelinePic(List<SinaTimelinePic> liSinaTimelinePic){
        sinaTimelinePicService.insertSinaTimelinePic(liSinaTimelinePic);
    }
    public void saveSinaTimeline(List<SinaTimeline> liSinaTimeline){
        sinaTimelineService.insertSinaTimeline(liSinaTimeline);
    }



}
