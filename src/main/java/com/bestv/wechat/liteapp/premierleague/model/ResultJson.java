package com.bestv.wechat.liteapp.premierleague.model;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Vem
 * @date 创建时间：2017年9月4日 下午12:20:58
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class ResultJson {
	private String resultCode;
	private String description;
	private ArrayList<JSONObject> datas;
	private long count;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<JSONObject> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<JSONObject> datas) {
		this.datas = datas;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		//返回的json串
		JSONObject jo = new JSONObject();
		jo.put("resultCode", this.resultCode);
		jo.put("description", this.description);
		jo.put("data", datas);
		jo.put("count", this.count);

		return jo.toJSONString();
	}

}
