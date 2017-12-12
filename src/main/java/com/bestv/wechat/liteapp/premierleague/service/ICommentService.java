package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.Comment;

import java.util.List;

public interface ICommentService {
    int insertComment(Comment comment);

    List<Comment> fetchCommentByTopicId(String strTopicId, String strOpenId);

    int thumbsupByTopicId(String strOpenId, long lCommentId, int iOperation);

    int updateThumbsupCountByCommentId(long lCommentId);

    int updateUnThumbsupCountByCommentId(long lCommentId);
}
