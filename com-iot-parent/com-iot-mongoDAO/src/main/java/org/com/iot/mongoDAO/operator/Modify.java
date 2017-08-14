package org.com.iot.mongoDAO.operator;

import java.util.Iterator;
import java.util.Set;

import org.com.iot.mongoDAO.MongoManager;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * 
 * @see mongodb update
 * @author Mickle
 * @date 29 Jul 2017
 */
public class Modify {
	
	private static DBCollection collection;
	
	public static Boolean ModifyInfo(String sCollection,JSONObject iJsObj) {
		
		boolean bool =false;
		collection = MongoManager.getDB().getCollection(sCollection);
		DBObject p = new BasicDBObject();
    	DBObject oldValue = collection.findOne(iJsObj);
    	if(oldValue==null){
    		
		} else {

			DBObject o = new BasicDBObject();
			o.putAll(oldValue);
			Set set = iJsObj.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				Object val = iJsObj.get(key);
				o.put(key, val);
			}
			
			WriteResult writeResult = collection.update(oldValue, o, true, false);
			if (null != writeResult){
				bool = false;
			}else{
				bool = true;
			}
			bool = true;

		}
		return bool;
	}	
	
	      
	
	
}
