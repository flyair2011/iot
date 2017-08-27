package com.iot.app.common.mapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.com.iot.common.StaticParameters;
import org.com.iot.function.DataHelper;

/**
 * @see 这个类加载所有的设备实体类，从APPLICATION_PROPERTY表<p><br>
 * 这个类的MAP包含了很重要的信息，通过这个配置希望以后如果需要添加设备，只需要在此表中添加一条数据，无需改动代码<p>
 * 会涉及到自子父表的映射与字段的转换，表名与设备参数的命名，这些信息必须一致
 * @author Mickle
 *
 */
public class DeviceMap {
	

	static Logger logger = Logger.getLogger(DeviceMap.class);
	
	public static Map<String, String> deviceMap = new HashMap<String, String>();
	
	static{
		List<Object[]> deviceList = DataHelper.getApplicationAttribteByScop(StaticParameters.SCOPE_DEVICE_CLASS);
		for (Object[] deviceClass : deviceList){
			logger.info(deviceClass[0].toString()+">>>"+ deviceClass[1].toString());
			deviceMap.put(deviceClass[0].toString(), deviceClass[1].toString());
		}
	}
	
}
