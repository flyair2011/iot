package com.iot.app.entity;

import java.io.Serializable;

public class DeviceTypeDto implements Serializable {

	private int id;
	private String deviceType;
	
	public DeviceTypeDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
