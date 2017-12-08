package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.model.Comment;
import com.bestv.wechat.liteapp.premierleague.service.impl.CommentService;
import com.bestv.wechat.liteapp.premierleague.utility.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {

    private Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/new", method = RequestMethod.POST)
    void newComment(HttpServletRequest request, HttpServletResponse response) {

        // 返回值相关
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();

        //主程序
        try {
            // 获取请求参数
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject joPostData = JSONObject.parseObject(sb.toString());

            String strSessionId = joPostData.getString("session_id");
            String strTopicId = joPostData.getString("topic_id");
            String strUserInfo = joPostData.getString("user_info");
            String strText = joPostData.getString("text");
            int iSource = Integer.parseInt(joPostData.getString("source"));
            // 判断是否是登录用户
            HttpSession session = request.getSession();
            strSessionId=session.getId();
            session.setAttribute(strSessionId, "mQP0MMi16UWrKHTgPCa5VA==+o23QP0eenOFaSrCsx1rkPxTSWx_8");
            String strSessionContent = String.valueOf(session.getAttribute(strSessionId));
            if (StringUtils.hasText(strSessionContent)) {
                if (StringUtils.hasText(strTopicId) && StringUtils.hasText(strText)) {
                    // 从session中获取openid
                    String[] strArray = String.valueOf(strSessionContent).split("\\+");
                    String strOpenId = strArray[1];
                    Comment comment = new Comment();
                    comment.setlTopicId(Long.parseLong(strTopicId));
                    comment.setStrOpenId(strOpenId);
                    comment.setStrUserInfo(strUserInfo);
                    comment.setStrText(strText);
                    comment.setiSource(iSource);
                    commentService.insertComment(comment);
                    mResponse.put("business_code", "successful");
                    mResponse.put("description", "评论成功");
                    mResponse.put("datas", jaDatas);
                    mResponse.put("page_info", mPageInfo);
                    joRsponse = JSONUtils.toJSONString(mResponse);
                } else {
                    mResponse.put("business_code", "failure");
                    mResponse.put("description", "主题或评论内容不能为空");
                    mResponse.put("datas", jaDatas);
                    mResponse.put("page_info", mPageInfo);
                    joRsponse = JSONUtils.toJSONString(mResponse);
                }
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
            mResponse.put("description", "评论出错");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            logger.error("评论出错：", e);
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
