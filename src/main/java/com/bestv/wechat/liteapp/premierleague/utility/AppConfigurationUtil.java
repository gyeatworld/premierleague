package com.bestv.wechat.liteapp.premierleague.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Vem
 * @date 创建时间：2017年9月5日 上午9:16:16
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class AppConfigurationUtil {

	private static final Logger logger = LogManager.getLogger(AppConfigurationUtil.class);
	private static final String APP_PROPERTIES_FILE = "app.properties";
	private static Properties props = new Properties();

	static {
		try {
			InputStream iniStr = AppConfigurationUtil.class.getClassLoader().getResourceAsStream(APP_PROPERTIES_FILE);
			if (iniStr != null) {
				Properties portalProperties = new Properties();
				portalProperties.load(iniStr);
				props.putAll(portalProperties);
			}
		} catch (IOException e) {
			logger.error("无法读取到配置文件:app.properties");
		}
	}
	private AppConfigurationUtil() {

	}

	public static String get(String strKey) {
		return props.getProperty(strKey);
	}
}
