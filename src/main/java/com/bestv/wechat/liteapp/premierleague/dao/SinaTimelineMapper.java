package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface SinaTimelineMapper {
    List<SinaTimeline> fetchSinaTimeline();

    int insertSinaTimeline(List<SinaTimeline> liSinaTimeline);
}
