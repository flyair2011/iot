package org.com.iot.mqtt_activemq.activemq.api;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.com.iot.mqtt_activemq.activemq.ConsumerService;
import org.com.iot.mqtt_activemq.activemq.ProducerService;
import org.com.iot.mqtt_activemq.http.HttpService;
import org.com.iot.mqtt_activemq.http.IHttpService;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * @see Io manager
 * @author Mickle
 * @date 5 Aug 2017
 */
@Component(value="ioManager")
public class IoManagerInterface implements IoInterface{


	//队列名gzframe.demo
    @Resource(name="queueDestination")
    private Destination queueDestination;

    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;
    
    //队列消息消费者
    @Resource(name="consumerService")
    private ConsumerService consumer;
    
	
	@Override
	public JSONObject messageReceive(JSONObject obj) {
		// TODO Auto-generated method stub
		System.out.println("------------receive message");
		TextMessage tm = consumer.receive(queueDestination);
		JSONObject json = null;
		try {
			json = (JSONObject) JSONObject.parse(tm.getText());
			IHttpService http = new HttpService();
			http.sendGet("http://localhost:8080/webApp", json.toJSONString());
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public void sendMessage(JSONObject obj) {
		
        producer.sendMessage(queueDestination, obj.toJSONString());
	}

	public ProducerService getProducer() {
		return producer;
	}

	public void setProducer(ProducerService producer) {
		this.producer = producer;
	}

	public ConsumerService getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerService consumer) {
		this.consumer = consumer;
	}
	
	
}
