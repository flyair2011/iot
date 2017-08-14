package org.com.iot.mqtt_activemq.mqtt;

import org.com.iot.common.StaticParameters;
import org.com.iot.mqtt_activemq.mqtt.interfaces.MqttRequesttBaseHandle;
import org.com.iot.mqtt_activemq.mqtt.interfaces.impl.BusDriver;
import org.com.iot.mqtt_activemq.mqtt.interfaces.impl.ClassDriver;

import com.alibaba.fastjson.JSONObject;

public class MessageDriverManager {

	public static void send(Object o){
		
		MqttRequesttBaseHandle mqqRequest = null;
		
		JSONObject json = JSONObject.parseObject(o.toString());
		if (json.get("topic").equals(StaticParameters.TOPIC_DEVICE)){
			mqqRequest = new BusDriver();
		}else if(json.get("topic").equals(StaticParameters.TOPIC_DEVICE_DATA)){
			mqqRequest = new ClassDriver();
		}
		mqqRequest.handle(json);
	}
}
