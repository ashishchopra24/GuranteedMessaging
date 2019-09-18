package com.ack.project;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageProducer {
    public static void main(String[] args) throws NamingException {

        InitialContext initialContext=new InitialContext();
        Queue requestQueue=(Queue)initialContext.lookup("queue/inbound");

        try(ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory();
            JMSContext jmsContext=activeMQConnectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE))
        {
            JMSProducer producer=jmsContext.createProducer();
            producer.send(requestQueue,"Message 1");

        }

    }
}
