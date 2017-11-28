package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.SinaUserMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaUser;
import com.bestv.wechat.liteapp.premierleague.service.ISinaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinaUserService implements ISinaUserService {

@Autowired
SinaUserMapper sinaUserMapper;

    @Override
    public int insertSinaUser(List<SinaUser> liSinaUser) {
        return sinaUserMapper.insertSinaUser(liSinaUser);
    }

    @Override
    public com.bestv.wechat.liteapp.premierleague.model.SinaUser fatchSinaUserById(String strId) {
        return null;
    }
}
