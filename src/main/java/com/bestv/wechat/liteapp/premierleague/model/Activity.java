package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class Activity {
    private int iId;
    private String strTitle;
    private String strPicUrl;
    private String strPageUrl;
    private int iStatus;
    private Timestamp tCreateTime;
    private Timestamp tUpdateTime;

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrPicUrl() {
        return strPicUrl;
    }

    public void setStrPicUrl(String strPicUrl) {
        this.strPicUrl = strPicUrl;
    }

    public String getStrPageUrl() {
        return strPageUrl;
    }

    public void setStrPageUrl(String strPageUrl) {
        this.strPageUrl = strPageUrl;
    }

    public int getiStatus() {
        return iStatus;
    }

    public void setiStatus(int iStatus) {
        this.iStatus = iStatus;
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
