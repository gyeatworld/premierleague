package com.bestv.wechat.liteapp.premierleague.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSourceContextHolder {
    private static Logger log = LogManager.getLogger(DataSourceContextHolder.class);

    //线程本地环境
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读库
     */
    public static void setRead() {
        local.set(DataSourceType.read.getType());
        log.info("数据库切换到读库...");
    }

    /**
     * 写库
     */
    public static void setWrite() {
        local.set(DataSourceType.write.getType());
        log.info("数据库切换到写库...");
    }

    public static String getReadOrWrite() {
        return local.get();
    }

    public static void clear(){
        local.remove();
    }
}
