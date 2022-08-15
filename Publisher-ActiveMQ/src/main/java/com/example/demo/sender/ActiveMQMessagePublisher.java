package com.example.demo.sender;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQMessagePublisher {

	@Autowired
	private JmsTemplate template;

	@Scheduled(cron = "*/10 * * * * *")
	public void sendMessage() {
		template.send("topic1", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				Message message = session.createTextMessage("From Sender at :" + new Date());
				return message;
			}

		});

		System.out.println("Message Sent");

	}

}
