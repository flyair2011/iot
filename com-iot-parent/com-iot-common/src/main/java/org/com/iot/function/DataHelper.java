package org.com.iot.function;

import java.util.List;

import org.com.iot.common.jdbc.JdbcUtil;

@SuppressWarnings("unchecked")
public class DataHelper {

	
	public static String getApplicationAttribte(String attributeName, String scope){
		
		String sql = "select attribute_value from application_property where attribute_name='"+attributeName+"' and scope = '"+scope+"'";
		List<String> data = JdbcUtil.getSSObjectsBySql(sql);
		String result = "";
		if (null != data && data.size() > 0){
			result = data.get(0);
		}
		return result;
	}
	
	private static String getWebRoot(){
		
		String sql = "select attribute_value from application_property where attribute_name='web_root' and scope = 'webapp'";
		List<String> data = JdbcUtil.getSSObjectsBySql(sql);
		String result = "";
		if (null != data && data.size() > 0){
			result = data.get(0);
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		String fd = DataHelper.getWebRoot();
		System.out.println(fd);
	}
}
