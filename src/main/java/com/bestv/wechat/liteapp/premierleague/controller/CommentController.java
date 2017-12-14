package com.bestv.wechat.liteapp.premierleague.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.model.Comment;
import com.bestv.wechat.liteapp.premierleague.service.impl.CommentService;
import com.bestv.wechat.liteapp.premierleague.utility.JSONUtil;
import com.bestv.wechat.liteapp.premierleague.utility.StringUtils;
import org.apache.commons.codec.binary.Base64;
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
public class CommentController {

    private Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    // 新建评论
    @RequestMapping(value = "/comment/new", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, timeout = 15)
    void newComment(HttpServletRequest request, HttpServletResponse response) {

        // 返回值相关
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();
        System.out.println("------------进入新建评论模块------------");
        //主程序
        try {
            // 获取请求参数
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line;
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

            System.out.println("session_id:" + strSessionId);
            System.out.println("topic_id:" + strTopicId);
            System.out.println("user_info:" + strUserInfo);
            System.out.println("text:" + strText);
            System.out.println("source:" + iSource);

            // 判断是否是小程序用户
            HttpSession session = request.getSession();
            //strSessionId = session.getId();
            //session.setAttribute(strSessionId, "mQP0MMi16UWrKHTgPCa5VA==+o23QP0eenOFaSrCsx1rkPxTSWx_8");
            String strSessionContent = String.valueOf(session.getAttribute(strSessionId));
            System.out.println(strSessionContent);
            if (StringUtils.hasText(strSessionContent)) {
                if (StringUtils.hasText(strTopicId) && StringUtils.hasText(strText)) {
                    // 从session中获取openid
                    String[] strArray = String.valueOf(strSessionContent).split("\\|\\|");
                    String strOpenId = strArray[1];
                    System.out.println(strOpenId);

                    Comment comment = new Comment();
                    comment.setlTopicId(Long.parseLong(strTopicId));
                    comment.setStrOpenId(strOpenId);
                    comment.setStrUserInfo(strUserInfo);
                    comment.setStrText(strText);
                    comment.setiSource(iSource);
                    System.out.println("-------------执行插入操作-----------");
                    commentService.insertComment(comment);
                    long lId = comment.getlId();
                    JSONObject joData = new JSONObject();
                    joData.put("id", lId);
                    jaDatas.add(joData);
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

    // 查询评论
    @RequestMapping(value = "/comment/list", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, timeout = 15)
    void fetchCommentByTopicId(HttpServletRequest request, HttpServletResponse response) {
        // 返回值相关
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();

        try {
            // 获取请求参数
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject joPostData = JSONObject.parseObject(sb.toString());

            String strTopicId = joPostData.getString("topic_id");
            String strSessionId = joPostData.getString("session_id");

            HttpSession session = request.getSession();
            //strSessionId = session.getId();
            //session.setAttribute(strSessionId, "mQP0MMi16UWrKHTgPCa5VA==+o23QP0eenOFaSrCsx1rkPxTSWx_8");
            String strSessionContent = String.valueOf(session.getAttribute(strSessionId));
            if (StringUtils.hasText(strSessionContent)) {

                String[] strArray = String.valueOf(strSessionContent).split("\\|\\|");
                String strOpenId = strArray[1];

                if (StringUtils.hasText(strTopicId)) {
                    List<Comment> liComments = commentService.fetchCommentByTopicId(strTopicId, strOpenId);
                    //返回评论列表
                    for (Comment comment : liComments) {
                        Map<String, Object> mComment = new HashMap<String, Object>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        mComment.put("id", comment.getlId());
                        mComment.put("text", comment.getStrText());
                        mComment.put("thumbsup_count", comment.getiThumbsupCount());
                        mComment.put("thumbsup_able", comment.getiThumbsupAble());
                        mComment.put("create_time", dateFormat.format(comment.gettCreateTime().getTime()+ (8 * 60 * 60 * 1000)));
                        mComment.put("user", JSONObject.parseObject(comment.getStrUserInfo()));
                        jaDatas.add(mComment);
                    }
                    mPageInfo.put("total", liComments.size());
                    mResponse.put("business_code", "successful");
                    mResponse.put("description", "查询评论成功");
                    mResponse.put("datas", jaDatas);
                    mResponse.put("page_info", mPageInfo);
                    joRsponse = JSONUtils.toJSONString(mResponse);
                } else {
                    mResponse.put("business_code", "failure");
                    mResponse.put("description", "topic_id为空");
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
            mResponse.put("description", "获取评论出错");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            logger.error("获取评论出错：", e);
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

    // 查询评论
    @RequestMapping(value = "/comment/thumbsup", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, timeout = 15)
    void thumbsupByTopicId(HttpServletRequest request, HttpServletResponse response) {
        // 返回值相关
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        Map<String, Object> mResponse = new HashMap<String, Object>();
        String joRsponse = "";
        JSONArray jaDatas = new JSONArray();
        Map<String, Object> mPageInfo = new HashMap<String, Object>();

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
            long lCommentId = joPostData.getLong("comment_id");
            int iOperation = joPostData.getInteger("operation");

            HttpSession session = request.getSession();
            //strSessionId = session.getId();
            //session.setAttribute(strSessionId, "mQP0MMi16UWrKHTgPCa5VA==+o23QP0eenOFaSrCsx1rkPxTSWx_8");
            String strSessionContent = String.valueOf(session.getAttribute(strSessionId));
            if (StringUtils.hasText(strSessionContent)) {

                String[] strArray = String.valueOf(strSessionContent).split("\\|\\|");
                String strOpenId = strArray[1];

                if (StringUtils.hasText(lCommentId)) {
                    if (iOperation == 1) {
                        // 点赞操作
                        commentService.thumbsupByTopicId(strOpenId, lCommentId, iOperation);
                        // 点赞数更新
                        commentService.updateThumbsupCountByCommentId(lCommentId);
                        mResponse.put("business_code", "successful");
                        mResponse.put("description", "点赞成功");
                        mResponse.put("datas", jaDatas);
                        mResponse.put("page_info", mPageInfo);
                        joRsponse = JSONUtils.toJSONString(mResponse);
                    } else if (iOperation == 0) {
                        commentService.thumbsupByTopicId(strOpenId, lCommentId, iOperation);
                        commentService.updateUnThumbsupCountByCommentId(lCommentId);
                        mResponse.put("business_code", "successful");
                        mResponse.put("description", "取消点赞成功");
                        mResponse.put("datas", jaDatas);
                        mResponse.put("page_info", mPageInfo);
                        joRsponse = JSONUtils.toJSONString(mResponse);
                    }

                } else {
                    mResponse.put("business_code", "failure");
                    mResponse.put("description", "comment_id为空");
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
            mResponse.put("description", "点赞出错");
            mResponse.put("datas", jaDatas);
            mResponse.put("page_info", mPageInfo);
            joRsponse = JSONUtils.toJSONString(mResponse);
            logger.error("点赞出错：", e);
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
