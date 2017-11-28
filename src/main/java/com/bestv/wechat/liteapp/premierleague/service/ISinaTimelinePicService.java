package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimelinePic;

import java.util.List;

public interface ISinaTimelinePicService {
    int insertSinaTimelinePic(List<SinaTimelinePic> liSinaTimelinePic);
    SinaTimelinePic fetchSinaTimelinePicBySinaTimelineId(String strSinaTimelineId);
}
