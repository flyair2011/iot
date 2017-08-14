package com.iot.app.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.iot.app.common.action.BaseAction;

@Namespace("/coreApp")
@ParentPackage("struts-default")
@Action(value="deviceData")
public class IODeviceDataAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void uploadData(){
		
	}

}
