package org.com.iot.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



/**
 * Get java real Bean tools
 * @author Mickle
 * @see 29 Jul 2017
 */
@SuppressWarnings("rawtypes")
public class GenericsUtils {
	
	
	public static Class getGenericClass(Class clazz){
	    return getGenericClass(clazz, 0);
	}

	public static Class getGenericClass(Class clazz, int index){
	    Type genType = clazz.getGenericSuperclass();
	    if ((genType instanceof ParameterizedType)) {
	       Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
	       if((params != null) && (params.length >= index - 1)) {
	          return (Class)params[index];
	       }
	    }
	    return null;
    }
}
