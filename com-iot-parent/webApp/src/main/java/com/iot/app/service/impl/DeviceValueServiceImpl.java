package com.iot.app.service.impl;

import org.com.iot.mongoDAO.IMongoDBDAO;
import org.com.iot.mongoDAO.impl.MongoDBDAOImpl;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.app.service.IDeviceValueService;

@Service("deviceValueService")
public class DeviceValueServiceImpl implements IDeviceValueService {

	IMongoDBDAO mongoDao = new MongoDBDAOImpl();

	@Override
	public JSONArray getDeviceValues(JSONObject params) {
		// TODO Auto-generated method stub
		return mongoDao.GetRecords("device_value", params);
	}

	@Override
	public JSONObject getDeviceValue(JSONObject params) {
		// TODO Auto-generated method stub
		return  mongoDao.GetOnlyRecord("device_value", params);
	}

	@Override
	public JSONObject save(JSONObject object) {
		// TODO Auto-generated method stub
		return mongoDao.save("device_value", object);
	}

	@Override
	public JSONObject getLatestDeviceValue(String orderBy, JSONObject params) {
		// TODO Auto-generated method stub
		return mongoDao.getLatestRecord("device_value", orderBy, params);
	}
	

}
