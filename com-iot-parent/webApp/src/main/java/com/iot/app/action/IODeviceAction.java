package com.iot.app.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.iot.app.common.action.BaseAction;
import com.iot.app.common.action.Result;
import com.iot.app.entity.DeviceDto;
import com.iot.app.service.IDeviceService;

@Namespace("/io")
@ParentPackage("struts-default")
@Action(value="device")
public class IODeviceAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The data is from  activeMQ be send via HttpClient
	 */
	public String data;
	
	@Autowired
	private IDeviceService deviceService;
	
	public void deviceRegister(){
		
		System.out.println("web:"+ data);
		Result result = new Result();
		result.setCode(0);
		result.setSuccess(true);
		try {
			DeviceDto device = new DeviceDto();
			deviceService.save(device);
			result.setMsg("Insert device success");
		} catch (Exception e) {
			// TODO: handle exception
			result.setCode(1);
			result.setMsg("Insert device fail");
			e.printStackTrace();
		}
		this.writeJson(result);
	}
	
	public void deviceLogOut(){
		System.out.println("web:"+ data);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
