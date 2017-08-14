package org.com.iot.mqtt_activemq.activemq.api;

import com.alibaba.fastjson.JSONObject;

/**
 * @see Io manager api interface
 * @author Mickle
 *
 */
public interface IoInterface {

	/**
	 * @see Receive message from mqtt and send to web application
	 * @param JSON obj
	 */
	public JSONObject messageReceive(JSONObject obj);
	
	
	/**
	 * @see Send message that from web application to mqtt via activemq
	 * @param obj
	 */
	public void sendMessage(JSONObject obj);
}
