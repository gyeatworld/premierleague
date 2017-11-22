package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthCode;

import java.util.List;

public interface ISinaAuthService {
    List<SinaAuthCode> getSinaAuth();
    int createSinaAuthCode(SinaAuthCode sinaAuthCode);
    int updateSinaAuthCode(SinaAuthCode sinaAuthCode);
    int createSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);
    int updateSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);
}
