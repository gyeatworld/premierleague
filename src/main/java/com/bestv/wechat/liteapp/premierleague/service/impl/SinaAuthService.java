package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.annotation.ReadDataSource;
import com.bestv.wechat.liteapp.premierleague.annotation.WriteDataSource;
import com.bestv.wechat.liteapp.premierleague.dao.SinaAuthMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthCode;
import com.bestv.wechat.liteapp.premierleague.service.ISinaAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SinaAuthService implements ISinaAuthService {

    @Autowired
    private SinaAuthMapper sinaAuthMapper;

    @Override
    @ReadDataSource
    public List<SinaAuthCode> getSinaAuth() {
        return sinaAuthMapper.getSinaAuthCode();
    }

    @Override
    @WriteDataSource
    public int createSinaAuthCode(SinaAuthCode sinaAuthCode) {
        return sinaAuthMapper.createSinaAuthCode(sinaAuthCode);
    }

    @Override
    @WriteDataSource
    public int updateSinaAuthCode(SinaAuthCode sinaAuthCode) {
        return sinaAuthMapper.updateSinaAuthCode(sinaAuthCode);
    }

    @Override
    @WriteDataSource
    public int createSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken) {
        return 0;
    }

    @Override
    @WriteDataSource
    public int updateSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken) {
        return sinaAuthMapper.updateSinaAuthAccessToken(sinaAuthAccessToken);
    }


}
