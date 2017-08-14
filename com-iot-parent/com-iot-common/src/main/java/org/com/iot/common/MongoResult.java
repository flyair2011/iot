package org.com.iot.common;

import com.alibaba.fastjson.JSONArray;

/**
 * @see Return Json result object of Operator MongoDb dao leayer 
 * @author Mickle
 * @date 29 Jul 2017
 */
public class MongoResult {

	private int code = 0;
	private boolean success = true;
	private JSONArray data = new JSONArray();
	private String msg = "";
	
	public MongoResult() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
