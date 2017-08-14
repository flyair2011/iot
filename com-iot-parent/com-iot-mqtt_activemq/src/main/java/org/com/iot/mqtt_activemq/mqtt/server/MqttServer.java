package org.com.iot.mqtt_activemq.mqtt.server;

import org.com.iot.mqtt_activemq.mqtt.MessageDriverManager;

public class MqttServer {
	public void startCase(String message){  
		
		System.out.println("接收到消息："+message);  
		MessageDriverManager.send(message);
       
    }  
}
