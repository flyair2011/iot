package com.iot.app.common.action;

/**
 * 
 * @date 25 Junl 2017
 * @see It is the response result,  all the return data will be included this class via JSON
 * @author Mickle
 * 
 */
public class Result {
	
	
	/*
	 * code define
	 * 0 - success
	 * 1 - username is error
	 * 2 - password is error
	 * 3 - session invalid
	 * 4 - request fail
	 */
	
	/**
	 * return status code, as above
	 */
	private int code = 0;
	
	/**
	 * return status message detail infomation
	 */
	private String msg = "The request is success";
	
	/**
	 * return simple data
	 */
	private String data = null;
	
	/**
	 * return data as list or map
	 */
	private Object obj;
	
	/**
	 * return status success or fail
	 */
	private Boolean success;

	
	public Boolean getSuccess() {
		
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Result() {
		
	}

	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(String data) {
		if(data!=null && !"".equals(data)) {
			this.data = data;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		if(data!=null && !"".equals(data)) {
			this.data = data;
		}
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		String json = "{code:"+code+", msg:'"+msg+"', data:"+data+"}";
		return json;
	}
	
	public static void main(String[] args) {
		System.out.println(new Result().toString());
	}

}

