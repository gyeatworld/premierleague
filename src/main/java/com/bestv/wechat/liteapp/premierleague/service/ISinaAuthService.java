package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;

import java.util.List;

public interface ISinaAuthService {

    SinaAuthAccessToken fetchSinaAuthAccessToken();

    int createSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);

    int updateSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);
}
