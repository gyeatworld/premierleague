package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaAuthService;
import com.bestv.wechat.liteapp.premierleague.utility.AppConfigurationUtil;
import com.bestv.wechat.liteapp.premierleague.utility.StringUtils;
import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/premierleague")
public class SinaAuthController {

    private Logger logger = LogManager.getLogger(SinaAuthController.class);

    private static String TOKEN_URL= AppConfigurationUtil.get("sina.access.token.url");
    private static String CLIENT_ID=AppConfigurationUtil.get("sina.app.key");
    private static String CLIENT_SECRET=AppConfigurationUtil.get("sina.app.secret");
    private static String REDIRECT_URI=AppConfigurationUtil.get("sina.callback");


    @Autowired
    SinaAuthService sinaAuthService;

    @RequestMapping(value = "/auth2/callback", method = RequestMethod.GET)
     void receiveCode(HttpServletRequest request) {
        logger.info("--------------------进入Sina的Auth流程-------------------");
        System.out.println(request.getServletPath());
        System.out.println(request.getQueryString());
        String strQueryString = request.getQueryString();
        String strCode = request.getParameter("code");
        //判断一下是否为code流程，并且是否拿到
        if (StringUtils.hasText(strQueryString) && StringUtils.hasText(strCode)) {
            logger.info("--------------------获取access_token-------------------");
            //去请求token
            String strPrarm="client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&grant_type=authorization_code&code="+strCode+"&redirect_uri="+REDIRECT_URI;
            try {

                String strResult = WebUtils.sendPost(TOKEN_URL, strPrarm);
                JSONObject joResult = JSONObject.parseObject(strResult);
                String strAccessToken = joResult.getString("access_token");
                //Sina API 即将作废该参数
                //String lRemindIn = joResult.getString("remind_in");
                String lExpiresIn = joResult.getString("expires_in");
                String strUid = joResult.getString("uid");
                SinaAuthAccessToken sinaAuthAccessToken=new SinaAuthAccessToken();
                sinaAuthAccessToken.setStrAccessToken(strAccessToken);
                sinaAuthAccessToken.setStrExpiresIn(lExpiresIn);
                sinaAuthAccessToken.setStrUid(strUid);
                sinaAuthService.updateSinaAuthAccessToken(sinaAuthAccessToken);
            }catch(Exception e){
                logger.error("获取AccessToken失败",e);
            }

        }else {
            logger.error("获取code失败，URL:"+request.getRequestURL());
        }
    }


}