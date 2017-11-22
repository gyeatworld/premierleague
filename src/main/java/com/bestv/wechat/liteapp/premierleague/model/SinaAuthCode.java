package com.bestv.wechat.liteapp.premierleague.model;

public class SinaAuthCode {
    private int iId;
    private String strCode;
    private long lCreateTime;
    private long lUpdateTime;

    public int getId() {
        return iId;
    }

    public void setId(int id) {
        this.iId = id;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) { this.strCode = strCode; }

    public long getlCreateTime() {
        return lCreateTime;
    }

    public void setlCreateTime(long lCreateTime) {
        this.lCreateTime = lCreateTime;
    }

    public long getlUpdateTime() { return lUpdateTime; }

    public void setlUpdateTime(long lUpdateTime) {
        this.lUpdateTime = lUpdateTime;
    }
}