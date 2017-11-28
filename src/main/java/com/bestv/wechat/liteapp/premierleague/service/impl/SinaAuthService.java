package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.annotation.ReadDataSource;
import com.bestv.wechat.liteapp.premierleague.annotation.WriteDataSource;
import com.bestv.wechat.liteapp.premierleague.dao.SinaAuthMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import com.bestv.wechat.liteapp.premierleague.service.ISinaAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SinaAuthService implements ISinaAuthService {

    @Autowired
    private SinaAuthMapper sinaAuthMapper;

    //@Override
    //@WriteDataSource
    //public int createSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken) {
     //   return 0;
    //}

    @Override
    @WriteDataSource
    public int updateSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken) {
        return sinaAuthMapper.updateSinaAuthAccessToken(sinaAuthAccessToken);
    }

    @Override
    @WriteDataSource
    public SinaAuthAccessToken fetchSinaAuthAccessToken() {
        return sinaAuthMapper.fetchSinaAuthAccessToken();
    }

}
