package com.iot.app.common.mapp;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.com.iot.function.DataHelper;

import com.alibaba.fastjson.JSONObject;

/**
 * @see 通过这个类与DeviceMap表，采用反射机制，动态的加载设备实体类<p>
 * @author Mickle
 *
 */
public class BuilderClass {
	
	
	public static final char UNDERLINE = '_';

	private BuilderClass() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 通过主数据名称获取对应类全名
	 * @param deviceName
	 * @return
	 * @throws Exception
	 */
	private static Class<?> getClass(String deviceName) throws Exception {
		
		String className = DeviceMap.deviceMap.get(deviceName);
		
		return className==null ? null : Class.forName(className);
		
	}
	
	/**
	 * @param deviceName
	 * @return
	 * @throws Exception
	 */
	public static Object build(String deviceName) throws Exception{
		
		Class<?> clazz = getClass(deviceName);
		
		return clazz==null ? null : clazz.newInstance();
	}
	
	
	/**
	 * @date 将设备的json对象转换为具体设备的实体对象
	 * @param data 设备的信息对象
	 * @param deviceName 设备的名称，从设备的请求中获取
	 * @return 设备的对象
	 */
	private static Map<String, Object> convertObj(JSONObject data){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Set set = data.keySet();
		Iterator iretator = set.iterator();
		while(iretator.hasNext()){
			//这里将设备属性的名称转换成实体类的名称，规则： 1 转换成小写。2 驼峰是命名 3 去掉下划线。
			String key = iretator.next().toString();
			String objKey = underlineToCamel(key);
			map.put(objKey, data.get(key));
		}
		return map;
	}
	
	
	/**
	 * @see 获取设备子类与父类所有的属性列
	 * @param fieldList
	 * @param clazz
	 */
	private static void getAllFields(List<Field> fieldList, Class<?> clazz){
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field:fields){
			fieldList.add(field);
		}
		
		Class<?> pClazz = clazz.getSuperclass();
		
		if(pClazz!=null && pClazz!=Object.class){
			getAllFields(fieldList, pClazz);
		}

	}
	
	public static Object build(String deviceName, JSONObject data) throws Exception{
		
		return builderObj(deviceName, convertObj(data));
	}
	
	
	/**
	 * 
	 * @param deviceName
	 * @param data
	 * @return
	 */
	public static Object builderObj(String deviceName, Map<String, Object> data){
		
		Object newObj = null;
		Class classz;
		
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.putAll(data);
		List<Field> fieldList = new ArrayList<Field>();
		
		try {
			// itself
			classz = Class.forName(DeviceMap.deviceMap.get(deviceName));
			getAllFields(fieldList, classz);
			//将设备的实际属性转换成对象中预存的属性
			List<Object[]> datas = DataHelper.getDeviceEmps(deviceName);
			if (null != datas && datas.size() > 0){
				for (Object[] oldAndNew : datas){
					String oldValue = oldAndNew[0].toString();// e.g  KEY_1->xxx KEY_2->yyy
					String newValue = oldAndNew[1].toString();
					if (newMap.containsKey(newValue)){
						String val = (String) data.get(newValue);
						newMap.remove(newValue);
						newMap.put(oldValue, val);
						
						data.remove(newValue);
						data.put(oldValue, val);
					}
				}
			}
			
			//判断传入进来的属性，目前对象是否已经包含并且配置预存的表中也是否已经存在，如果没有则插入 需要判断父类和子类
			Set set = data.keySet();
			Iterator iretator = set.iterator();
			String fieldName = "";
			while(iretator.hasNext()){
				String key = iretator.next().toString();
				boolean isExist = false;
				for (Field fdName : fieldList){
					fieldName = fdName.getName();
					if (fieldName.equals("serialVersionUID")) {
						continue;
					}
					
					if (fieldName.toUpperCase().equals(key.toUpperCase())){
						isExist = true;
						break;
					}
				}
				
				//如果有新的属性加入
				if (!isExist){
					//插入
					String okey = DataHelper.getNextKey(deviceName);
					DataHelper.insertDeviceEmp(okey, key, deviceName);
					String val = (String) data.get(key);
					newMap.remove(key);
					newMap.put(okey, val);
				}
			}
			newObj = build(deviceName);
			createNewInstance(fieldList, newObj, newMap);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newObj;
	}
	
	private static Object createNewInstance(List<Field> fields, Object newObj, Map<String, Object> data){
		
		
		for (Field field : fields){
			if (field.equals("serialVersionUID")){
				continue;
			}
			
			field.setAccessible(true);
			try {
				String type = field.getType().getName();
				String name = field.getName();
				if (name.startsWith("key_")){//预留字段
					name = name.toUpperCase();
				}
				Object v = data.get(name);
				Object value = null;
				
				if (v==null||type.endsWith("String")){
					value = v;
				} else if (type.endsWith("Integer")){
					value = Integer.valueOf(v.toString());
				} else if (type.endsWith("Double") || type.endsWith("double")){
					value = Double.valueOf(v.toString());
				} else if (type.endsWith("Date")){
					//2014-09-12T16:53:14.018+08:00
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = sdf.parseObject(v.toString());
				}else if (type.equals("int")){
					value = Integer.parseInt(v.toString());
				}
				if (null != value){
					field.set(newObj, value);	
				}
				if (type.endsWith("Double") && null == value){
					value = Double.parseDouble("0");
					field.set(newObj, value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return newObj;
	}
	

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * 
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        
        param = param.toLowerCase();
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串2
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
       JSONObject item = new JSONObject();
       item.put("MAC", "DFC-FD-CADFA");
       item.put("KEY", "234DWER");
       item.put("CODING", "SDFSDF");
       item.put("STATUS", "2");
       item.put("DEVICE_NAME", "电冰箱");
       item.put("KFT", "XX");
       item.put("TEF", "XXT");
       item.put("TEFfff", "3234");
       
      Object obj =  build("air_conditioner", item);
      
      System.out.println(obj);
      System.exit(0);
    }
	
//	public static void main(String[] args) {
//		new BuilderClass().builderObj(null, "air_conditioner");
//	}
	
}
