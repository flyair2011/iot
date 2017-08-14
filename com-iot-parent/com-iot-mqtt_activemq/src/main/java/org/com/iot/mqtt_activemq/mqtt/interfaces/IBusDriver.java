package org.com.iot.mqtt_activemq.mqtt.interfaces;

/**
 * @see Device register, logout
 * @author Mickle
 * @date 5 Aug 2017
 */
public interface IBusDriver {

	void deviceRegister(String msg);
	
	void deviceLogOut(String msg);
}
