package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.SinaTimelineMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.service.ISinaTimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinaTimelineService implements ISinaTimelineService {

    @Autowired
    SinaTimelineMapper sinaTimelineMapper;

    @Override
    public int insertSinaTimeLine(List<SinaTimeline> liSinaTimeline) {
        return sinaTimelineMapper.insertSinaTimeline(liSinaTimeline);
    }

    @Override
    public List<SinaTimeline> fetchSinaTimeLine() {
        return null;
    }

}
