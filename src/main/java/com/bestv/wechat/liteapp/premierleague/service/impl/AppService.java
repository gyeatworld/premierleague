package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.AppMapper;
import com.bestv.wechat.liteapp.premierleague.model.AppInit;
import com.bestv.wechat.liteapp.premierleague.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService implements IAppService {
    @Autowired
    AppMapper appMapper;

    @Override
    public AppInit fetchAppInit() {
        return appMapper.fetchAppInit();
    }
}
