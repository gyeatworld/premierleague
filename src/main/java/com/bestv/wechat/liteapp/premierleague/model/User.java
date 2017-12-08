package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class User {
    private String strId;
    private String strOpenId;
    private Timestamp tsCreateTime;
    private Timestamp tsUpdateTime;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrOpenId() {
        return strOpenId;
    }

    public void setStrOpenId(String strOpenId) {
        this.strOpenId = strOpenId;
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
