package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int insertComment(Comment comment);
    List<Comment> fetchCommentByTopicId(long lTopicId);
}
