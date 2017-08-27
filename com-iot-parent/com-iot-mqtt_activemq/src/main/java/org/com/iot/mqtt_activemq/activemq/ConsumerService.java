package org.com.iot.mqtt_activemq.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.com.iot.mqtt_activemq.http.HttpService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	 	@Resource(name="jmsTemplate")
	    private JmsTemplate jmsTemplate;
	    
	 	HttpService http = new HttpService();
	 	
	    /**
	     * 接收消息
	     */
	    public TextMessage receive(Destination destination) {
	        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
	        try {
	            System.out.println("从队列" + destination.toString() + "收到了消息：\t" + tm.getText());
	            http.sendGet("", "");
	            
	            
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	        
	        return tm;
	        
	    }
}
