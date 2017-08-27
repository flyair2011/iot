package com.iot.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name="device")
@Inheritance(strategy=InheritanceType.JOINED)
public class DeviceDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String mac;
	protected String key;
	protected String coding;
	protected int deviceType;
	protected String desction;
	protected int dataType;
	protected int status;
	protected Date createTime;
	protected Date updateTime;
	protected String deviceName;
	protected int createUser;
	
	
	
	public DeviceDto() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name="ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	@Column(name="DEVICE_TYPE")
	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getDesction() {
		return desction;
	}

	public void setDesction(String desction) {
		this.desction = desction;
	}

	@Column(name="DATA_TYPE")
	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		updateTime = updateTime;
	}

	@Column(name="DEVICE_NAME")
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name="CREATE_USER")
	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
}
