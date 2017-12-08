package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class UserInfo {
    private long lId;
    private String strOpenId;
    private String strEncryptedOpenId;
    private Timestamp tsCreateTime;
    private Timestamp tsUpdateTime;

    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    public String getStrOpenId() {
        return strOpenId;
    }

    public void setStrOpenId(String strOpenId) {
        this.strOpenId = strOpenId;
    }

    public String getStrEncryptedOpenId() {
        return strEncryptedOpenId;
    }

    public void setStrEncryptedOpenId(String strEncryptedOpenId) {
        this.strEncryptedOpenId = strEncryptedOpenId;
    }

    public Timestamp getTsCreateTime() {
        return tsCreateTime;
    }

    public void setTsCreateTime(Timestamp tsCreateTime) {
        this.tsCreateTime = tsCreateTime;
    }

    public Timestamp getTsUpdateTime() {
        return tsUpdateTime;
    }

    public void setTsUpdateTime(Timestamp tsUpdateTime) {
        this.tsUpdateTime = tsUpdateTime;
    }
}
