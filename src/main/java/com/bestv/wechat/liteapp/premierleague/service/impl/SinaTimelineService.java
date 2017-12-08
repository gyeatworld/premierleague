package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.SinaTimelineMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.service.ISinaTimelineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinaTimelineService implements ISinaTimelineService {

    @Autowired
    SinaTimelineMapper sinaTimelineMapper;
    @Autowired
    PageInterceptor pageInterceptor;
    @Override
    public int insertSinaTimeline(List<SinaTimeline> liSinaTimeline) {
        return sinaTimelineMapper.insertSinaTimeline(liSinaTimeline);
    }

    @Override
    public PageInfo fetchSinaTimeline(int iPage,int iCount) {
        PageHelper pageHelper=(PageHelper)pageInterceptor.plugin(new PageHelper());
        pageHelper.startPage(iPage,iCount);
        pageHelper.orderBy("a.id desc");
        List<SinaTimeline> liSinaTimelines=sinaTimelineMapper.fetchSinaTimelineWithoutRange(iPage,iCount);
        PageInfo pageInfo=new PageInfo(liSinaTimelines);
        return pageInfo;
    }

    @Override
    public PageInfo fetchSinaTimeline(int iPage,int iCount,long iRange) {
        PageHelper pageHelper=(PageHelper)pageInterceptor.plugin(new PageHelper());
        pageHelper.startPage(iPage,iCount);
        pageHelper.orderBy("a.id desc");
        List<SinaTimeline> liSinaTimelines=sinaTimelineMapper.fetchSinaTimeline(iRange);
        PageInfo pageInfo=new PageInfo(liSinaTimelines);
        return pageInfo;
    }

}
