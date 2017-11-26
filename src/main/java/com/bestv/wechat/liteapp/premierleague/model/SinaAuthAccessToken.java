package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class SinaAuthAccessToken {
    private int iId;
    private String strAccessToken;
    private String strUid;
    private String strExpiresIn;
    private long lCreateTime;
    private long lUpdateTime;

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getStrAccessToken() {
        return strAccessToken;
    }

    public void setStrAccessToken(String strAccessToken) {
        this.strAccessToken = strAccessToken;
    }

    public String getStrUid() {
        return strUid;
    }

    public void setStrUid(String strUid) {
        this.strUid = strUid;
    }

    public String getStrExpiresIn() {
        return strExpiresIn;
    }

    public void setStrExpiresIn(String strExpiresIn) {
        this.strExpiresIn = strExpiresIn;
    }

    public long getlCreateTime() {
        return lCreateTime;
    }

    public void setlCreateTime(Timestamp lCreateTime) {
        this.lCreateTime = lCreateTime.getTime() + (8 * 60 * 60 * 1000);
    }

    public long getlUpdateTime() {
        return lUpdateTime;
    }

    public void setlUpdateTime(Timestamp lUpdateTime) {
        this.lUpdateTime = lUpdateTime.getTime() + (8 * 60 * 60 * 1000);
    }
}
