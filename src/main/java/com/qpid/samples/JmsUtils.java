/**
 * 
 */
package com.qpid.samples;

import java.util.Hashtable;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsUtils implements Constants {

    private static Connection connection;

    /**
     * @return the connection
     * @throws JMSException
     * @throws NamingException
     */
    public static Connection getConnection() throws NamingException, JMSException {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    public static Connection createConnection() throws NamingException, JMSException {

        Hashtable<String, String> hashtable = new Hashtable<>();

        hashtable.put("connectionfactory.SBCF", "amqps://" + SBUS_NAME + ".servicebus.windows.net?amqp.traceFrames=true&jms.prefetchPolicy.queuePrefetch=0");

        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, QPID_CONNECTION_FACTORY_CLASS);

        Context context = new InitialContext(hashtable);
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("SBCF");

        Connection connection = connectionFactory.createConnection(USERNAME, PASSWORD);
        connection.setExceptionListener(new ExceptionListener() {
            
            @Override
            public void onException(JMSException exception) {
                exception.printStackTrace();
            }
        }); // Settted ExceptionListener
        connection.start();

        return connection;
    }



}
