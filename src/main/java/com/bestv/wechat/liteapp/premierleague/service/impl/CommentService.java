package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.CommentMapper;
import com.bestv.wechat.liteapp.premierleague.model.Comment;
import com.bestv.wechat.liteapp.premierleague.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> fetchCommentByTopicId(String strTopicId,String strOpenId) {
        return commentMapper.fetchCommentByTopicId(strTopicId,strOpenId);
    }

    @Override
    public int thumbsupByTopicId(String strOpenId, long lCommentId, int iOperation) {
        return commentMapper.thumbsupByTopicId(strOpenId, lCommentId, iOperation);
    }

    @Override
    public int updateThumbsupCountByCommentId(long lCommentId) {
        return commentMapper.updateThumbsupCountByCommentId(lCommentId);
    }

    @Override
    public int updateUnThumbsupCountByCommentId(long lCommentId) {
        return commentMapper.updateUnThumbsupCountByCommentId(lCommentId);
    }


}
