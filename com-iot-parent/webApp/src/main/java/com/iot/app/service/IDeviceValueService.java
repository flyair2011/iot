package com.iot.app.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IDeviceValueService {

	public JSONArray getDeviceValues(JSONObject params);
	
	public JSONObject getDeviceValue(JSONObject params);
	
	public JSONObject getLatestDeviceValue(String orderBy, JSONObject params);
	
	public JSONObject save(JSONObject object);
}
