package com.ack.project;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MessageConsumer {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext=new InitialContext();
        Queue requestQueue=(Queue)initialContext.lookup("queue/inbound");


        try(ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory();
            JMSContext jmsContext=activeMQConnectionFactory.createContext())
        {
            JMSConsumer consumer=jmsContext.createConsumer(requestQueue);
            TextMessage message=(TextMessage) consumer.receive();
            System.out.println(message.getText());

        }

    }
}
