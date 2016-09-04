package com.joerajeev.activemq.receiver;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * An asynchronous receiver that listens for messages.
 * @author jmotha
 *
 */
public class AsyncReceiver implements MessageListener{
	
	public AsyncReceiver(){
		try {
			ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = cf.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("BILL_PAY");
			
			MessageConsumer receiver = session.createConsumer(queue);
			receiver.setMessageListener(this);
			System.out.println("Waiting for messages");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		new Thread(){

			@Override
			public void run() {
				new AsyncReceiver();
			}
			
			
		}.start();

	}


	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("Message received : "+textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
