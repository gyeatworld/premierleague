package com.bestv.wechat.liteapp.premierleague.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestv.wechat.liteapp.premierleague.config.DataSourceConfig;
import com.bestv.wechat.liteapp.premierleague.model.SinaAuthAccessToken;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeLine;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaAuthService;
import com.bestv.wechat.liteapp.premierleague.service.impl.SinaTimeLineService;
import com.bestv.wechat.liteapp.premierleague.utility.AppConfigurationUtil;
import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Date;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Component
public class SinaTimeLineFunction {

    @Autowired
    SinaAuthService sinaAuthService;

    private static String SINA_TIME_LINE_URL = AppConfigurationUtil.get("sina.time.line.url");

    public ArrayList<SinaTimeLine> pullTimeLineFromSina() {
        //获取Access Token
        //SinaAuthAccessToken sinaAuthAccessToken=sinaAuthService.fetchSinaAuthAccessToken();
        //String strAccessToken=sinaAuthAccessToken.getStrAccessToken();
        String strAccessToken = "2.00gPHyGCGZPleDc44e41c29fwGX5MB";
        String strPrarm = "access_token=" + strAccessToken;
        //获取博文
        String strResult = WebUtils.sendGet(SINA_TIME_LINE_URL, strPrarm);
        JSONObject jsonObjectResult = JSONObject.parseObject(strResult);
        JSONArray jaTimeLine = JSONArray.parseArray(jsonObjectResult.getString("statuses"));
        SinaTimeLine sinaTimeLine = new SinaTimeLine();
        ArrayList<SinaTimeLine> alSinaTimeLine = new ArrayList<SinaTimeLine>();
        for (Object oSinaTimeLine : jaTimeLine) {
            JSONObject joTimeLine = JSONObject.parseObject(String.valueOf(oSinaTimeLine));
            System.out.println(joTimeLine.getString("text"));
        }

        return null;
    }

}
