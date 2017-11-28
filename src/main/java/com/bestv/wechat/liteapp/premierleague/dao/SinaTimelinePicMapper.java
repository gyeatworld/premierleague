package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimelinePic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SinaTimelinePicMapper {
    int insertSinaTimelinePic(List<SinaTimelinePic> liSinaTimelinePic);
}
