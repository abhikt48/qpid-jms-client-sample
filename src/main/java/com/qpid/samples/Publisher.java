
package com.qpid.samples;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import org.apache.qpid.jms.message.JmsMessage;
import org.apache.qpid.jms.provider.amqp.message.AmqpJmsMessageFacade;
import org.apache.qpid.proton.amqp.Symbol;

public class Publisher {
    
    private String contentType = "text/json";

    public void publishMessage() throws NamingException, JMSException {
        
        Connection connection = JmsUtils.getConnection();
        Session session = connection.createSession();
        TextMessage jmsMessage = session.createTextMessage("ABCD");
        ((AmqpJmsMessageFacade) ((JmsMessage) jmsMessage).getFacade()).setContentType(Symbol.valueOf(contentType ));
        
        Destination destination = session.createQueue(Constants.PUBLISHER_QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(destination);
        
        messageProducer.send(jmsMessage);
        
        System.out.println("** Published Message Successfully **");
    }
    
    public static void main(String[] args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.publishMessage();
    }

}
