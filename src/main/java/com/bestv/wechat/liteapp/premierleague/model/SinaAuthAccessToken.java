package com.bestv.wechat.liteapp.premierleague.model;

public class SinaAuthAccessToken {
    private int id;
    private String strAccessToken;
    private String strUid;
    private String strExpiresIn;
    private long lCreateTime;
    private long lUpdateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setlCreateTime(long lCreateTime) {
        this.lCreateTime = lCreateTime;
    }

    public long getlUpdateTime() {
        return lUpdateTime;
    }

    public void setlUpdateTime(long lUpdateTime) {
        this.lUpdateTime = lUpdateTime;
    }
}
