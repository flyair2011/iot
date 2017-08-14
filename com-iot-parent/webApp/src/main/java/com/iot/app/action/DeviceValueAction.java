package com.iot.app.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.com.iot.common.StaticParameters;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.iot.app.common.action.BaseAction;
import com.iot.app.common.action.Result;
import com.iot.app.entity.UserInfoDto;
import com.iot.app.service.IDeviceValueService;

@Namespace("/coreApp")
@ParentPackage("struts-default")
@Action(value="deviceVal")
public class DeviceValueAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Result result = null;
	
	@Autowired
	private IDeviceValueService deviceValueService;
	
	private String usreName;
	
	private String mac;
	
	/**
	 * Get the latest value of the device
	 */
	public void getDeviceLatestValue(){
		result = new Result();
		result.setCode(0);
		result.setSuccess(true);
		try {
			UserInfoDto userDto = (UserInfoDto) session.getAttribute(StaticParameters.SESSION_USER_NAME);
			if (null != userDto && userDto.getUserName().equals(usreName)){
				JSONObject params = new JSONObject();
				params.put("mac", mac);
				JSONObject data = deviceValueService.getLatestDeviceValue("CREATE_TIME", params);
				result.setObj(data);
			}else{
				result.setMsg("Sessin 失效");
				log.error("Sessin 失效");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("查询"+mac+"最新值失败");
			result.setCode(1);
		}
		
		this.writeJson(result);
	}

	public IDeviceValueService getDeviceValueService() {
		return deviceValueService;
	}

	public void setDeviceValueService(IDeviceValueService deviceValueService) {
		this.deviceValueService = deviceValueService;
	}

	public String getUsreName() {
		return usreName;
	}

	public void setUsreName(String usreName) {
		this.usreName = usreName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	
}
