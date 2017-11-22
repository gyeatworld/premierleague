package com.bestv.wechat.liteapp.premierleague;

import com.bestv.wechat.liteapp.premierleague.utility.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PremierleagueApplicationTests {

	private Logger logger = LogManager.getLogger(PremierleagueApplicationTests.class);

	@Test
	public void getCode() {
		//获取code测试
		String strPrarm="client_id=3350713188&redirect_uri=http://54.222.216.13:8080/premierleague/auth2/callback&response_type=code";
		String s = WebUtils.sendPost("https://api.weibo.com/oauth2/authorize", strPrarm);
		logger.info(s);
	}
	@Test
	public void getSinaVideo() {
		//获取code测试
		String strPrarm="uid=1935251240&mid=4176517179344115&keys=4176514810698617&type=feedvideo";
		String s = WebUtils.sendPost("https://weibo.com/aj/video/playstatistics?ajwvr=6", strPrarm);
		logger.info(s);
	}
	@Test
	public void getUserTimeline() {
		//获取code测试
		String strPrarm="access_token=2.00gPHyGCGZPleDc44e41c29fwGX5MB";
		String s = WebUtils.sendGet("https://api.weibo.com/2/statuses/home_timeline.json", strPrarm);
		logger.info(s);
	}

}
