package org.com.iot.mongoDAO.impl;

import java.util.List;

import org.com.iot.common.Dispose;
import org.com.iot.common.MongoResult;
import org.com.iot.mongoDAO.IMongoDBDAO;
import org.com.iot.mongoDAO.operator.Insert;
import org.com.iot.mongoDAO.operator.Query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MongoDBDAOImpl implements IMongoDBDAO {

	MongoResult result = new MongoResult();
	
	@Override
	public JSONObject save(String sCollection, JSONObject data) {
		// TODO Auto-generated method stub
		
		Dispose.preInsertRecord(data);
		
		if (Insert.PostColRecord(sCollection, data)){
			result.setCode(0);
			result.setSuccess(true);
			List<JSONObject> datas = Query.Query(sCollection, data);
			String json = JSON.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss");
			result.setData(JSONArray.parseArray(json));
		}else{
			result.setCode(1);
			result.setSuccess(false);
		}
		return Dispose.objToJson(result);
	}

	@Override
	public JSONObject DeleteRecords(String sCollection, JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(String sCollection, JSONObject oldValue, JSONObject newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray GetRecords(String sCollection, JSONObject params) {
		// TODO Auto-generated method stub
		List list = Query.Query(sCollection, params);
		String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
		return JSONArray.parseArray(json);
	}
	
	@Override
	public JSONObject GetOnlyRecord(String sCollection, JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject GetRecord(String sCollection, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getLatestRecord(String sCollection, String orderBy, JSONObject params) {
		// TODO Auto-generated method stub
		
		return Query.QueryLatest(sCollection, orderBy, params);
	}

}
