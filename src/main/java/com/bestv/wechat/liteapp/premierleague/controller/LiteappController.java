package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.utility.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/premierleague")
public class LiteappController {

    private Logger logger = LogManager.getLogger(LiteappController.class);

    private static String strJsCodeToSessionUrl = AppConfigurationUtil.get("wx.jscode2session.url");
    private static String strAppId = AppConfigurationUtil.get("wx.app.id");
    private static String strAppSecret = AppConfigurationUtil.get("wx.app.secret");

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    String initApp() {

        return "sss";
    }

    @RequestMapping(value = "/user/init", method = RequestMethod.POST)
    void initUser(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        // 返回值相关
        PrintWriter out = null;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();
        try {
            // 获取request
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String strLine = null;
            StringBuilder sbRequest = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                sbRequest.append(strLine);
            }
            String strPostData = sbRequest.toString();
            // 获取code
            JSONObject joPostData = JSONObject.parseObject(strPostData);
            Object oCode = joPostData.get("code");

            if (StringUtils.hasText(oCode)) {
                StringBuilder sbParam = new StringBuilder();
                sbParam.append("appid=" + strAppId).append("&secret=" + strAppSecret).append("&js_code=" + oCode.toString())
                        .append("&grant_type=authorization_code");
                // 去获取openid和session_key,unionid暂时不知道怎么用
                String result = WebUtils.sendGet(strJsCodeToSessionUrl, sbParam.toString());
                //String result="{\"session_key\":\"mQP0MMi16UWrKHTgPCa5VA==\",\"expires_in\":7200,\"openid\":\"o23QP0eenOFaSrCsx1rkPxTSWx_8\"}";
                JSONObject joResult = JSONObject.parseObject(result);
                String strOpenId = joResult.getString("openid");
                String strSessionKey = joResult.getString("session_key");
                // 拿到openId才进行后续操作
                if (StringUtils.hasText(strOpenId)) {
                    // 生成一个返回的SessionId，并将用户的openId存入HttpSession
                    HttpSession session = request.getSession();

                    // 本地生成一个3rd_session，返回给前端,用以判断登录状态
                    String mySessionId = SessionUtil.generate3rdSession();

                    // String sessionId="616D3F6F406068B158B71D65C1F195E1";
                    // 把openid和session_key存入session
                    session.setAttribute(mySessionId, strSessionKey + "+" + strOpenId);

                    JSONObject jo = new JSONObject();
                    jo.put("sessionId", mySessionId);
                    jo.put("JSESSIONID", session.getId());
                    jaDatas.add(jo);

                    mResponse.put("business_code", "successful");
                    mResponse.put("description", "获取3rd_session成功");
                    mResponse.put("datas", jaDatas);
                    mResponse.put("page_info", mPageInfo);
                    joRsponse = JSONUtils.toJSONString(mResponse);
                } else {
                    jaDatas.add(joResult);
                    mResponse.put("business_code", "failure");
                    mResponse.put("description", "获取OpenId失败");
                    mResponse.put("datas", jaDatas);
                    mResponse.put("page_info", mPageInfo);
                    joRsponse = JSONUtils.toJSONString(mResponse);
                }
            }else{
                jaDatas.add(joPostData);
                mResponse.put("business_code", "failure");
                mResponse.put("description", "获取code失败");
                mResponse.put("datas", jaDatas);
                mResponse.put("page_info", mPageInfo);
                joRsponse = JSONUtils.toJSONString(mResponse);
            }
        } catch (Exception e) {
            JSONObject jo = new JSONObject();
            jo.put("Exception", e.toString());
            jaDatas.add(jo);
            mResponse.put("business_code", "failure");
            mResponse.put("description", "用户初始化错误");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            // TODO Auto-generated catch block
            logger.error("生成3rd_session失败",e);
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
