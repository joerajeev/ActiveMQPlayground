package com.joerajeev.activemq.receiver;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws JMSException {
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = cf.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("NAB_BILL_PAYMENTS");
		MessageConsumer consumer = session.createConsumer(queue);
		TextMessage message = (TextMessage) consumer.receive(2000);
		if(message != null){
			System.out.println("message received : "+ message.getText());
		}else{
			System.out.println("No message.");
		}
		connection.close();
		
	}
}
