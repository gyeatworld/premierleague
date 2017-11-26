package com.bestv.wechat.liteapp.premierleague.dao;

import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SinaAuthMapper {

    int createSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);

    int updateSinaAuthAccessToken(SinaAuthAccessToken sinaAuthAccessToken);

    SinaAuthAccessToken fetchSinaAuthAccessToken();

}
