package org.com.iot.mqtt_activemq.mqtt.interfaces;

import javax.annotation.Resource;

import org.com.iot.mqtt_activemq.activemq.api.IoManagerInterface;

import com.alibaba.fastjson.JSONObject;

/**
 * @see Mqtt send command to ActiveMQ
 * @author Mickle
 * @date 5 Aug 2017
 */
public abstract class MqttRequesttBaseHandle implements MqttRequestInterface{

	@Resource(name="ioManager")
	IoManagerInterface ioManager;
	@Override
	public void handle(Object obj) {
		// TODO Auto-generated method stub
		//send to actibeMQ
		JSONObject json = (JSONObject) JSONObject.parse(obj.toString());
		ioManager.sendMessage(json);
		System.out.println("发送至ActiveMQ");
	}
}
