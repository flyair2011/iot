package com.iot.app.common.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @date 25 Junl 2017
 * @see It is the Base Action class
 * @author Mickle
 * 
 */
public class BaseAction  extends ActionSupport {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();
	public SimpleDateFormat sdf_nyr = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat sdf_nyrsfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final Logger log = Logger.getLogger(BaseAction.class);
	
	
	
	public void writeJson(Object object){
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	
	public void responseComplete(Object object){
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void responseComplete(String json) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeJsonFileUpload(Object object){
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
