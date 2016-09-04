package com.joerajeev.activemq.sender;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**Example of a sender in a Point to point messaging model*/
public class Sender {

	public static void main(String[] args) throws JMSException {
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = cf.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = session.createQueue("BILL_PAY");
		MessageProducer sender = session.createProducer(queue);
		sender.send(session.createTextMessage("PAY origin $100"));
		System.out.println("Message Sent");
		
		connection.close();
	}

}

