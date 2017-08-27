package com.iot.app.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.aspectj.weaver.ast.Call;
import org.com.iot.common.StaticParameters;
import org.springframework.beans.factory.annotation.Autowired;

import com.iot.app.common.action.BaseAction;
import com.iot.app.common.action.Result;
import com.iot.app.entity.DeviceDto;
import com.iot.app.entity.UserInfoDto;
import com.iot.app.service.IDeviceService;

/**
 * @date 26 Junk 2017
 * @see core business submit Equement interface, used by application
 * @author Mickle
 *
 */
@Namespace("/coreApp")
@ParentPackage("struts-default")
@Action(value="device")
public class DeviceInfoAction extends BaseAction{

	
	private static final long serialVersionUID = 1L;
	

	@Autowired
	private IDeviceService deviceService;
	
	
	/**
	 * device id
	 */
	private int id;
	
	private String mac;
	
	/**
	 * Get devices by username, It is from app
	 */
	private String usreName;

	private Result result = null;

	/**
	 * @see find all equemnets by user
	 * <p>
	 *    The core business verify the user whether it has right via session <br>
	 *    and get all the user's device
	 * </p>
	 * 
	 * @param usre's id
	 */
	public void getAllDevices(){
		
		
		result = new Result();
		result.setCode(0);
		result.setSuccess(true);
		try {
			UserInfoDto userDto = (UserInfoDto) session.getAttribute(StaticParameters.SESSION_USER_NAME);
			if (null != userDto && userDto.getUserName().equals(usreName)){
				Object[] params = {userDto.getId()};
				List<DeviceDto> devices = deviceService.getObjects("where createUser = ? ", params);
				result.setObj(devices);
			}else{
				result.setMsg("Sessin fail");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(1);
		}
		
		this.writeJson(result);
		
	}
	
	/**
	 * @see Call access device interface, the core business layer through the session management to determine <br>
	 * 		whether the user login, if you log on, access to the database from the database user <br>
	 * 		permissions and MAC address of the same unique device information.<br>
	 * @param username and mac
	 */
	public void getDevice(){
		
		Result result = new Result();
		result.setCode(0);
		result.setSuccess(true);
		try {
			UserInfoDto userInfo = (UserInfoDto) session.getAttribute(StaticParameters.SESSION_USER_NAME);
			if(null != userInfo){
				Object[] params = {usreName, mac};
				DeviceDto deviceDto = deviceService.getObject("where createUser = ? and mac = ?", params);
				result.setObj(deviceDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.setCode(1);
			e.printStackTrace();
		}
		this.writeJson(result);
	}
	
	
	/**
	 * @see if user has log on, get the latest value from mongodb and return
	 * @param username and mac
	 */
	public void getDeviceRecord(){
		
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public String getUsreName() {
		return usreName;
	}

	public void setUsreName(String usreName) {
		this.usreName = usreName;
	}

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
	
	
}
