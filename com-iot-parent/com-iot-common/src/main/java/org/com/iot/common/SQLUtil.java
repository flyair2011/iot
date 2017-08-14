package org.com.iot.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * sql工具类
 * @author Mickle
 *
 */
public class SQLUtil {
	
	/**
	 * 根据map拼装where语句
	 * @param map
	 * @return
	 */
	 public static  String createWhereSQL(Map<String,Object> map){
		String hql=" 1=1";	
		String key=null;
		for(Entry<String,Object> entry:map.entrySet()){
			key=entry.getKey();
			Object value=entry.getValue();
			if(null!=value&&!"".equals(value)){
				hql=hql+key;
			}			
		}		
		 return hql;		 
	 }
	 /**
	  * 根据map拼装参数
	  * @param map
	  * @return
	  */
	 public static  Object[] createParams(Map<String,Object> map){
		 	List<Object> paramsList = new ArrayList<Object>();
		 	Object value=null;
			for(Entry<String,Object> entry:map.entrySet()){
				value=entry.getValue();
				if(null!=value&&!"".equals(value)){
					paramsList.add(value);
				}				
			}			
			 return paramsList.toArray();			 
		 }
}
