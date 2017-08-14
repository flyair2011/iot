package org.com.iot.common;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @see 
 * @author Micle
 * @date 29 Jul 2017
 */
public class Dispose {

	/**
	 * @see <P>Set the obj defaul value of create time and update time</P>
	 * @param iJsObj
	 */
	public static void preInsertRecord(JSONObject iJsObj) {
		
		iJsObj.put("CREATE_TIME", DateUtil.formatDate(new Date()));
		iJsObj.put("UPDATE_TIME", DateUtil.formatDate(new Date()));
		
	}
	
	/**
	 * @see Change the update time
	 * @param iJsObj
	 */
	public static void preUpdateRecord(JSONObject iJsObj){
		iJsObj.put("UPDATE_TIME", DateUtil.formatDate(new Date()));
	}
	
	public static JSONObject objToJson(Object object){
		String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		JSONObject jsonObj = JSONObject.parseObject(json);
		return jsonObj;
		
	}
	
}
