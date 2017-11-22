package com.bestv.wechat.liteapp.premierleague.utility;

import java.util.UUID;

/** 
 * @author Vem
 * @date 创建时间：2017年8月30日 下午5:00:53 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class UUIDUtil {
 
	    public static String generateUuid() {  
	        String uuid = UUID.randomUUID().toString().replace("-", ""); //获取UUID并转化为String对象  
	        uuid = uuid.replace("-", "");               //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可  
	        return uuid;  
	    }  
}
