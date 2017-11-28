package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;

import java.util.List;

public interface ISinaTimelineService {

    List<SinaTimeline> fetchSinaTimeLine();

    int insertSinaTimeLine(List<SinaTimeline> alSinaTimeline);
}
