package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.SinaTimelinePicMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimelinePic;
import com.bestv.wechat.liteapp.premierleague.service.ISinaTimelinePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinaTimelinePicService implements ISinaTimelinePicService {

    @Autowired
    SinaTimelinePicMapper sinaTimelinePicMapper;

    @Override
    public int insertSinaTimelinePic(List<SinaTimelinePic> liSinaTimelinePic) {
        return sinaTimelinePicMapper.insertSinaTimelinePic(liSinaTimelinePic);
    }

    @Override
    public SinaTimelinePic fetchSinaTimelinePicBySinaTimelineId(String strSinaTimelineId) {
        return null;
    }
}
