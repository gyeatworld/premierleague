package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.SinaUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SinaUserMapper {
    int insertSinaUser(List<SinaUser> liSinaUser);

    SinaUser fatchSinaUserById(String strId);
}
