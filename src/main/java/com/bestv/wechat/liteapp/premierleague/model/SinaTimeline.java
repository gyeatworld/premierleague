package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;
import java.util.List;

public class SinaTimeline {

    private String strId;//微博ID
    private String strText;//微博信息内容
    private List<String> liPicUrl;
    private Timestamp tCreatedAt;//微博创建时间
    private String strSource;//来源
    private int iRepostsCount;//转发数
    private int iCommentsCount;//评论数
    private int iAttitudesCount;
    private String strSinaUserId;//微博作者的用户信息字段
    private Timestamp tCreateTime;
    private Timestamp tUpdateTime;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrText() {
        return strText;
    }

    public void setStrText(String strText) {
        this.strText = strText;
    }

    public List<String> getLiPicUrl() {
        return liPicUrl;
    }

    public void setLiPicUrl(List<String> liPicUrl) {
        this.liPicUrl = liPicUrl;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public Timestamp gettCreatedAt() {
        return tCreatedAt;
    }

    public void settCreatedAt(Timestamp tCreatedAt) {
        this.tCreatedAt = tCreatedAt;
    }

    public int getiRepostsCount() {
        return iRepostsCount;
    }

    public void setiRepostsCount(int iRepostsCount) {
        this.iRepostsCount = iRepostsCount;
    }

    public int getiCommentsCount() {
        return iCommentsCount;
    }

    public void setiCommentsCount(int iCommentsCount) {
        this.iCommentsCount = iCommentsCount;
    }


    public int getiAttitudesCount() {
        return iAttitudesCount;
    }

    public void setiAttitudesCount(int iAttitudesCount) {
        this.iAttitudesCount = iAttitudesCount;
    }

    public String getStrSinaUserId() {
        return strSinaUserId;
    }

    public void setStrSinaUserId(String strSinaUserId) {
        this.strSinaUserId = strSinaUserId;
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
