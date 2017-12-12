package com.bestv.wechat.liteapp.premierleague.model;

public class Thumbsup {
    private String strTopicId;
    private long lCommentId;
    private int status;

    public String getStrTopicId() {
        return strTopicId;
    }

    public void setStrTopicId(String strTopicId) {
        this.strTopicId = strTopicId;
    }

    public long getlCommentId() {
        return lCommentId;
    }

    public void setlCommentId(long lCommentId) {
        this.lCommentId = lCommentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
