package com.iot.app.entity;

import java.io.Serializable;

public class DeviceDataTypeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String DeviceDataType;
	
	public DeviceDataTypeDto() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceDataType() {
		return DeviceDataType;
	}

	public void setDeviceDataType(String deviceDataType) {
		DeviceDataType = deviceDataType;
	}
	
	
}
