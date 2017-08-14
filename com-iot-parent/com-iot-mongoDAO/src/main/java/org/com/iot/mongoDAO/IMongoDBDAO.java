package org.com.iot.mongoDAO;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @see Mongodb interfaces
 * @author Mickle
 * @date 29 Jul 2017
 */
public interface IMongoDBDAO {
	
	public JSONObject save(String collectionName, JSONObject data);
	
	public JSONObject DeleteRecords(String sCollection, JSONObject params);
	
	public JSONObject update(String sCollection, JSONObject oldValue, JSONObject newValue);
	
	public JSONArray GetRecords(String sCollection, JSONObject params);
	
	public JSONObject GetRecord(String sCollection, String id);
	
	public JSONObject GetOnlyRecord(String sCollection, JSONObject params);

	public JSONObject getLatestRecord(String sCollection,String orderBy, JSONObject params);

}
