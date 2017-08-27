package org.com.iot.function;

import java.util.List;

import org.com.iot.common.jdbc.JdbcUtil;

/**
 * @see operator application properties
 * @author Mickle
 *
 */
@SuppressWarnings("unchecked")
public class DataHelper {


	/**
	 * Get property value from the table of APPLICATION_PROPERTIY
	 * @param attributeName
	 * @param scope
	 * @return
	 */
	public static List<String> getApplicationAttribte(String attributeName, String scope){
		
		String sql = "select attribute_value from application_property where 1=1 ";
		
		if (null != attributeName && !"".equals(attributeName)){
			
			sql += " and attribute_name='"+attributeName+"'";
		}
		sql += " and scope = '"+scope+"'";
		
		
		List<String> data = JdbcUtil.getSSObjectsBySql(sql);
		return data;
	}
	
	public static List<Object[]> getApplicationAttribteByScop(String scope){
		
		String sql = "select attribute_name, attribute_value from application_property where 1=1 ";
		sql += " and scope = '"+scope+"'";
		List<Object[]> data = JdbcUtil.getSSObjectsBySql(sql);
		return data;
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
	
	public static String getNextKey(String tableName){
		String sql = "SELECT max(ATTRIBUTE_NAME) FROM DEVICE_EMP WHERE TABLE_NAME = '"+tableName+"'";
		List datas = JdbcUtil.getSSObjectsBySql(sql);
		String key = "KEY_1";
		if (null != datas && datas.size() > 0){
			String keyv = (String) datas.get(0);
			if (null != keyv && !"".equals(keyv)){
				int kv = Integer.parseInt(keyv.substring(keyv.indexOf("_")+1));
				key = "KEY_"+(kv + 1);	
			}
		}
		return key;
	}
	
	public static void insertDeviceEmp(String oldName, String newName, String tableName){
		String sql = "INSERT INTO DEVICE_EMP (ATTRIBUTE_NAME, ATTRIBUTE_VALUE, TABLE_NAME) VALUES ('"+oldName+"', '"+newName+"', '"+tableName+"')";
		JdbcUtil.dml(sql);
	}
	
	public static List<Object[]> getDeviceEmps(String tableName){
		
		List<Object[]> listdata = null;
		String sql = "SELECT ATTRIBUTE_NAME, ATTRIBUTE_VALUE FROM DEVICE_EMP WHERE TABLE_NAME = '"+tableName+"'";
		List list = JdbcUtil.getSSObjectsBySql(sql);
		if (null != list && list.size() > 0){
			listdata = list;
		}
		return listdata;
	}
	
	public static List<String[]> getDeviceEmps(String tableName, String newValue){
		
		List<String[]> listdata = null;
		String sql = "SELECT ATTRIBUTE_NAME, ATTRIBUTE_VALUE FROM DEVICE_EMP WHERE TABLE_NAME = '"+tableName+"' and ATTRIBUTE_VALUE = '"+newValue+"'";
		List list = JdbcUtil.getSSObjectsBySql(sql);
		if (null != list && list.size() > 0){
			listdata = list;
		}
		return listdata;
	}
	
	public static void main(String[] args) {
		
		String key = "KEY_2";
		System.out.println(key.substring(key.indexOf("_")+1));
	}
}
