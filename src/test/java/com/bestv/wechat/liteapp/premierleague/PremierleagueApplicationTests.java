package com.bestv.wechat.liteapp.premierleague;


import com.bestv.wechat.liteapp.premierleague.function.SinaTimelineFunction;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeline;
import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@ComponentScan(basePackages = "com.bestv.wechat.liteapp.premierleague.function")
public class PremierleagueApplicationTests {
/*
	private Logger logger = LogManager.getLogger(PremierleagueApplicationTests.class);

	@Autowired
	SinaTimelineFunction sinaTimeLineFunction;


	@Test
	public void getCode() {
		//获取code测试
		String strPrarm="client_id=3350713188&redirect_uri=http://54.222.216.13:8080/premierleague/auth2/callback&response_type=code";
		String s = WebUtils.sendPost("https://api.weibo.com/oauth2/authorize", strPrarm);
		logger.info(s);
	}

	@Test
	public void pullSinaTimeLine() {
		sinaTimeLineFunction.pullTimelineFromSina();
		System.out.println("-------Test Finish------");
	}

	@Test
	public void getSinaTimeLine() {
		PageInfo pageInfo = sinaTimeLineFunction.getTimeline(1,18);
		logger.info(pageInfo.toString());
		System.out.println("-------Test Finish------");
	}
	@Test
	public void otherTest() {
		System.out.println("-------Test Begin------");
		Date date =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
		Date d = null;
		try {
			d = sdf.parse(new Date().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(d.toString());
		System.out.println("-------Test Finish------");
	}
*/
}
