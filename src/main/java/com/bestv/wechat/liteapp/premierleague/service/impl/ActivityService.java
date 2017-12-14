package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.ActivityMapper;
import com.bestv.wechat.liteapp.premierleague.model.Activity;
import com.bestv.wechat.liteapp.premierleague.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Activity fetchActivity() {
        return activityMapper.fetchActivity();
    }
}
