package org.com.iot.mqtt_activemq.mqtt.interfaces.impl;

import org.com.iot.mqtt_activemq.mqtt.interfaces.IBusDriver;
import org.com.iot.mqtt_activemq.mqtt.interfaces.MqttRequesttBaseHandle;

public class BusDriver extends MqttRequesttBaseHandle implements IBusDriver {

	@Override
	public void deviceRegister(String msg) {
		// TODO Auto-generated method stub
		this.handle(msg);
	}

	@Override
	public void deviceLogOut(String msg) {
		// TODO Auto-generated method stub
		this.handle(msg);
	}


}
