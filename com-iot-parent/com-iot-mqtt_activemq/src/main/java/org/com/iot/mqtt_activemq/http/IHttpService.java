package org.com.iot.mqtt_activemq.http;

/**
 * @see Mock http to access web application
 * @author Mickle
 * @date 5 Aug 2017
 */
public interface IHttpService {

	/**
	 * @see send get request
	 * @param url
	 * @param param
	 * @return
	 */
	public String sendGet(String url, String param);
	
	/**
	 * @see send post request
	 * @param url
	 * @param param
	 * @return
	 */
	public String sendPost(String url, String param);
}
