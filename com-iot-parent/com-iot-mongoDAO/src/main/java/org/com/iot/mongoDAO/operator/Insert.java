package org.com.iot.mongoDAO.operator;

import java.util.Map;

import org.com.iot.mongoDAO.MongoManager;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * 
 * @see mongodb insert
 * @author Mickle
 * @date 29 Jul 2017
 */
public class Insert {
	
	
	private static DBCollection collection;
	private static DB db;
	
	/*
	 * (non-Javadoc)
	 */
	public static  Boolean PostColRecord(String sCollection, JSONObject iJson) {
		boolean bool = false;
		collection = MongoManager.getDB().getCollection(sCollection);
		if (iJson == null || iJson.isEmpty()) {
			
		} else {
			DBObject p = new BasicDBObject();
			p.putAll(iJson);
			try {
				WriteResult writeResult = collection.insert(p);
				if (null != writeResult){
					bool = false;
				}else{
					bool = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}
	
	
	

}
