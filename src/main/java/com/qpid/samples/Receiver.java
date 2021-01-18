package com.qpid.samples;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.NamingException;

public class Receiver {
    
    public void receiveMessage() throws NamingException, JMSException {
        Connection connection = JmsUtils.getConnection();
        Session session = connection.createSession(false, 101);
        Destination destination = session.createQueue(Constants.RECEIVER_QUEUE_NAME);
        
        MessageConsumer consumer = session.createConsumer(destination);
        Message jmsMessage = consumer.receive(10000); // Receive one message
        
        if(jmsMessage == null) {
            System.out.println(System.currentTimeMillis() + "*** There is no message for consumption......");
        }else {
            System.out.println(System.currentTimeMillis() + "*********** Received Message ID :: " + jmsMessage.getJMSMessageID());
        }
        
        System.out.println("** Published Message Successfully **");
    }
    
    public static void main(String[] args) throws Exception {
        Receiver receiver = new Receiver();
        receiver.receiveMessage();
    }
}
