package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int insertComment(Comment comment);

    List<Comment> fetchCommentByTopicId(String strTopicId, String strOpenId);

    int thumbsupByTopicId(String strOpenId, long lCommentId, int iOperation);

    int updateThumbsupCountByCommentId(long lCommentId);

    int updateUnThumbsupCountByCommentId(long lCommentId);
}
