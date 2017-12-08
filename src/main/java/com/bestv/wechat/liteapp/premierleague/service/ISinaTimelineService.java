package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISinaTimelineService {

    PageInfo fetchSinaTimeline(int iPageNum, int iPageSize);

    PageInfo fetchSinaTimeline(int iPageNum, int iPageSize, long iRange);

    int insertSinaTimeline(List<SinaTimeline> alSinaTimeline);
}
