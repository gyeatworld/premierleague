package com.bestv.wechat.liteapp.premierleague.model;

import java.sql.Timestamp;

public class SinaTimelinePic {
    private long lId;
    private String strSinaTimelineId;
    private String strThumbnailPic;
    private String strMiddlePic;
    private String strOriginalPic;
    private Timestamp tCreateTime;
    private Timestamp tUpdateTime;

    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    public String getStrSinaTimelineId() {
        return strSinaTimelineId;
    }

    public void setStrSinaTimelineId(String strSinaTimelineId) {
        this.strSinaTimelineId = strSinaTimelineId;
    }

    public String getStrThumbnailPic() {
        return strThumbnailPic;
    }

    public void setStrThumbnailPic(String strThumbnailPic) {
        this.strThumbnailPic = strThumbnailPic;
    }

    public String getStrMiddlePic() {
        return strMiddlePic;
    }

    public void setStrMiddlePic(String strMiddlePic) {
        this.strMiddlePic = strMiddlePic;
    }

    public String getStrOriginalPic() {
        return strOriginalPic;
    }

    public void setStrOriginalPic(String strOriginalPic) {
        this.strOriginalPic = strOriginalPic;
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
