package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class Comment {
    private long lId;
    private long lTopicId;
    private String strOpenId;
    private String strUserInfo;
    private String strText;
    private int iSource;
    private int iThumbsupCount;
    private int iThumbsupAble;
    private Timestamp tCreateTime;
    private Timestamp tUpdateTime;

    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    public long getlTopicId() {
        return lTopicId;
    }

    public void setlTopicId(long lTopicId) {
        this.lTopicId = lTopicId;
    }

    public String getStrOpenId() {
        return strOpenId;
    }

    public void setStrOpenId(String strOpenId) {
        this.strOpenId = strOpenId;
    }

    public String getStrUserInfo() {
        return strUserInfo;
    }

    public void setStrUserInfo(String strUserInfo) {
        this.strUserInfo = strUserInfo;
    }

    public String getStrText() {
        return strText;
    }

    public void setStrText(String strText) {
        this.strText = strText;
    }

    public int getiSource() {
        return iSource;
    }

    public void setiSource(int iSource) {
        this.iSource = iSource;
    }

    public int getiThumbsupCount() {
        return iThumbsupCount;
    }

    public void setiThumbsupCount(int iThumbsupCount) {
        this.iThumbsupCount = iThumbsupCount;
    }

    public int getiThumbsupAble() {
        return iThumbsupAble;
    }

    public void setiThumbsupAble(int iThumbsupAble) {
        this.iThumbsupAble = iThumbsupAble;
    }

    public Timestamp gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Timestamp tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public Timestamp gettUpdateTime() {
        return tUpdateTime;
    }

    public void settUpdateTime(Timestamp tUpdateTime) {
        this.tUpdateTime = tUpdateTime;
    }
}
