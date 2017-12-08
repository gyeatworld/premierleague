package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.CommentMapper;
import com.bestv.wechat.liteapp.premierleague.model.Comment;
import com.bestv.wechat.liteapp.premierleague.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }
}
