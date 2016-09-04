package com.joerajeev.activemq.sender;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class Sender {

	public static void main(String[] args) throws JMSException {
		 ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		 Connection connection = cf.createConnection();
		 connection.start();
		 Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		 Queue queue = session.createQueue("NAB_BILL_PAYMENTS");
		 MessageProducer sender = session.createProducer(queue);
		 
		 TextMessage payBillMsg = session.createTextMessage("pay energy $100");
		 sender.send(payBillMsg);
		 System.out.println("Message sent");
		 connection.close();
	}

}
