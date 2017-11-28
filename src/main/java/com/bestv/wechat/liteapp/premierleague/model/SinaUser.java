package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class SinaUser {

    private String strId;//用户ID
    private String strScreenName;//用户昵称
    private String strDescription;//用户个人描述
    private String strUrl;//用户博客地址
    private String strProfileUrl;//用户的微博统一URL地址
    private String strProfileImageUrl;//用户头像地址（中图），50×50像素
    private String strAvatarLarge;//用户头像地址（大图），180×180像素
    private String strAvatarHd;//用户头像地址（高清），高清头像原图;
    private int iFollowersCount;//粉丝数
    private int iFriendsCount;//关注数
    private int iStatusesCount;//微博数
    private int iFavouritesCount;//收藏数
    private Timestamp tCreateTime;
    private Timestamp tUpdateTime;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrScreenName() {
        return strScreenName;
    }

    public void setStrScreenName(String strScreenName) {
        this.strScreenName = strScreenName;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrUrl() {
        return strUrl;
    }

    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public String getStrProfileUrl() {
        return strProfileUrl;
    }

    public void setStrProfileUrl(String strProfileUrl) {
        this.strProfileUrl = strProfileUrl;
    }

    public String getStrProfileImageUrl() {
        return strProfileImageUrl;
    }

    public void setStrProfileImageUrl(String strProfileImageUrl) {
        this.strProfileImageUrl = strProfileImageUrl;
    }

    public String getStrAvatarLarge() {
        return strAvatarLarge;
    }

    public void setStrAvatarLarge(String strAvatarLarge) {
        this.strAvatarLarge = strAvatarLarge;
    }

    public String getStrAvatarHd() {
        return strAvatarHd;
    }

    public void setStrAvatarHd(String strAvatarHd) {
        this.strAvatarHd = strAvatarHd;
    }

    public int getiFollowersCount() {
        return iFollowersCount;
    }

    public void setiFollowersCount(int iFollowersCount) {
        this.iFollowersCount = iFollowersCount;
    }

    public int getiFriendsCount() {
        return iFriendsCount;
    }

    public void setiFriendsCount(int iFriendsCount) {
        this.iFriendsCount = iFriendsCount;
    }

    public int getiStatusesCount() {
        return iStatusesCount;
    }

    public void setiStatusesCount(int iStatusesCount) {
        this.iStatusesCount = iStatusesCount;
    }

    public int getiFavouritesCount() {
        return iFavouritesCount;
    }

    public void setiFavouritesCount(int iFavouritesCount) {
        this.iFavouritesCount = iFavouritesCount;
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
