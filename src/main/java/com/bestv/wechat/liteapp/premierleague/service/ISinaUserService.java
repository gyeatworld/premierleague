package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaUser;

import java.util.List;

public interface ISinaUserService {
    int insertSinaUser(List<SinaUser> liSinaUser);

    SinaUser fatchSinaUserById(String strId);
}
