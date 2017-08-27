package org.com.iot.common;

/**
 * @see final and static paramaters
 * @author Mickle
 * @date 29 Jul 2017
 */
public class StaticParameters {

	/**
	 * The seesion user's name
	 */
	public final static String SESSION_USER_NAME = "SESSION_USER";
	
	
	
	
	/**************************** MONGO DB PROPERTIES ***************************************/
	public final static String DB_NAME = "m_iot";
	
	public final static String DB_HOST = "localhost";
	
	public final static int DB_PORT = 27017;
	
	public final static int DB_POOLSIZE = 100;// 连接数量  
	
	public final static int DB_BLOCKSIZE = 100; // 等待队列长度  
	
	
	
	/******************************** MQTT PROPERTIES **************************************/
	
	public final static String HOST_NAME = "";
	public final static String UUID = "";
	public final static String TOKEN = "";
	public final static String USERNAME= "admin" ;  
	public final static String PASSPORD= "password" ;
	
	public final static String TOPIC_DEVICE = "device";
	public final static String TOPIC_DEVICE_DATA = "device_data";
	
	public final static String COMMAND_REGISTER_DEVICE = "COMMAND_REGISTER_DEVICE";
	public final static String COMMAND_LOGINOUT_DEVICE = "COMMAND_LOGINOUT_DEVICE";
	public final static String COMMAND_GETLATEST_DEVICE = "COMMAND_GETLATEST_DEVICE";
	
	
	/**
	 * property scope
	 */
	public final static String SCOPE_DEVICE_CLASS = "deviceclass";
	public final static String SCOPE_WEB_APP = "webapp";
	public final static String SCOPE_COMMAND_CLASS = "command";
	
	
	
}
