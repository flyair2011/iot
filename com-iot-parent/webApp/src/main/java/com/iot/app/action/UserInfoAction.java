package com.iot.app.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.com.iot.common.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.iot.app.common.action.BaseAction;
import com.iot.app.common.action.Result;
import com.iot.app.entity.UserInfoDto;
import com.iot.app.service.IUserInfoService;

/**
 * 
 * @date 26 Junl 2017
 * @see The business core submit interface for application
 * @author Mickle
 *
 */
@Namespace("/coreApp")
@Action(value="user")
@ParentPackage("struts-default")
public class UserInfoAction extends BaseAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName = "";
	private String password = "";
	private String realName = "";
	private String userLocation = "";
	

	@Autowired
	private IUserInfoService userInfoService;
	
	/**
	 * @see <P> Create a new account and add to mysql db
	 *    need user submit usrName and password<P>
	 *    
	 *    step1: find user by userName, if is not already exist.</BR>
	 *    step2: insert or return 
	 * </p>
	 * 
	 * @return code 0: success; 1: userName already exists,  2: password is null; 3 username is null ;
	 * 
	 */
	public void register (){
		
		Result result = new Result();
		try {
			result.setCode(0);
			result.setSuccess(true);
			if (null != userName && !"".equals(userName)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(userName, userName);
				UserInfoDto userDto = userInfoService.getObjectByExample(params);
				if (null != userDto){
					if (null != password && !"".equals(password)){
						String password_des = AESUtils.encrypt(password);
						userDto = new UserInfoDto();
						userDto.setUserName(userName);
						userDto.setPassword(password_des);
						userInfoService.save(userDto);
						session.setAttribute("SESSION_USERINFO", userDto);
					}else{
						result.setCode(2);
					}
				}else{
					//already exist
					result.setCode(1);
				}
				
			}else{
				result.setCode(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.writeJson(result);
	}
	
	/**
	 * @see <P> User login from app
	 *    need user submit usrName and password
	 * </p>
	 * 
	 * @return code 0: success; 1: userName error; 2: password error
	 * 
	 */
	public void login (){
		
		Result result = new Result();
		try {
			if (null != userName && !"".equals(userName)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(userName, userName);
				UserInfoDto userDto = userInfoService.getObjectByExample(params);
				if (null != userDto){
					String pwd = AESUtils.encrypt(password);
					if (userDto.getPassword().equals(pwd)){
						result.setCode(0);
						result.setObj(userDto);
					}else{
						result.setCode(2);
					}
				}else{
					result.setCode(1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setCode(1);
		}
		this.writeJson(result);
	}
	
	/**
	 * @see <P> change user's information
	 * </p>
	 * 
	 * @return code 0: success; 1:  fail
	 * 
	 */
	public void modifyUser(){

		Result result = new Result();
		UserInfoDto userDtpo = userInfoService.get(id);
		if (null != userDtpo){
			try {
				userDtpo.setPassword(AESUtils.encrypt(password));
				userInfoService.update(userDtpo);
				result.setCode(0);
				result.setSuccess(true);
			} catch (Exception e) {
				result.setCode(1);
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		this.writeJson(result);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
