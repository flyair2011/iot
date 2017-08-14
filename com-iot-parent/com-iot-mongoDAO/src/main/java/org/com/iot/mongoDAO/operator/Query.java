package org.com.iot.mongoDAO.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.com.iot.mongoDAO.MongoManager;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * 
 * @see mongodb query
 * @author Mickle
 * @date 29 Jul 2017
 */
public class Query {
	
	private static DBCollection collection;
	static Gson gson = new Gson();
	
	public static boolean QueryRecord(String sCollection,String sId,Map oMap) {
		boolean bool = false;
		collection = MongoManager.getDB().getCollection(sCollection);
		ObjectId oId = new ObjectId();
		try {
			oId = new ObjectId(sId.trim());
		} catch (Exception e) {
			return true;
		}
		DBObject q = collection.findOne(oId);
		if(q==null){
		}else{
		String id = q.get("_id").toString();	
	    oMap.putAll(q.toMap());
	    if(oMap.containsKey("_id")){
	    	oMap.remove("_id");
	    }
	    oMap.put("id", id);
		bool = true;
		}
		return bool;
	}
	
	
	/**
	 * 
	 * @param sCollection
	 * @param iJsObj params
	 * @param oJsObj return object
	 * @return
	 */
	public static boolean QueryRecord(String sCollection,JSONObject iJsObj,JSONObject oJsObj) {
		
		boolean bool = false;
		
		collection = MongoManager.getDB().getCollection(sCollection);
		DBObject p =new BasicDBObject();
	    p = (DBObject)JSON.parse(iJsObj.toString());
		
		DBObject q = collection.findOne(p);
		if(q==null){
			
		}else{
		  Gson gson = new Gson();
		  oJsObj = JSONObject.parseObject(gson.toJson(q));
		  bool=true;	
		}
		return bool;
	}
	
	
	
	public static List Query(String sCollection, JSONObject iJsObj) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		collection = MongoManager.getDB().getCollection(sCollection);
		DBObject query = new BasicDBObject();
		query.putAll(iJsObj);
		DBCursor cur = collection.find(query);
		JSONObject oJsObj = new JSONObject();
		DBObject obj = null;
		for (; cur.hasNext();) {
			obj = cur.next();
			oJsObj = JSONObject.parseObject(gson.toJson(obj));
			list.add(oJsObj);
		}
		return list;
	}

	public static List Query(String sCollection) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		collection = MongoManager.getDB().getCollection(sCollection);
		DBCursor cur = collection.find();
		for (; cur.hasNext();) {
			DBObject obj = cur.next();
			JSONObject oJsObj = JSONObject.parseObject(gson.toJson(obj));
			list.add(oJsObj);
		}
		return list;

	}
	
	public static JSONObject QueryLatest(String sCollection, String orderby, JSONObject params){
		collection = MongoManager.getDB().getCollection(sCollection);
		DBObject orderBy = new BasicDBObject();
		orderBy.put("UPDATE_TIME", -1);
		DBCursor cur = collection.find().sort(orderBy).limit(1);
		DBObject data = cur.next();
		JSONObject oJsObj = JSONObject.parseObject(gson.toJson(data));
		return oJsObj;
	}

}
