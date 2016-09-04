package com.joerajeev.activemq.receiver;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SynchronousReceiver {

	public static void main(String[] args) throws JMSException {
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = cf.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("BILL_PAY");
		
		MessageConsumer receiver = session.createConsumer(queue);
		TextMessage message = (TextMessage) receiver.receive(1000);
		
		if(message != null){
			System.out.println("Message received : " + message.getText());
		}else{
			System.out.println("No messages to receive");
		}
		connection.close();
		
	}
}
