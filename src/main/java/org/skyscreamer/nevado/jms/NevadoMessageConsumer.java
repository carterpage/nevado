package org.skyscreamer.nevado.jms;

import javax.jms.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpage
 * Date: 3/21/12
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class NevadoMessageConsumer implements MessageConsumer {
    private Destination _destination;

    public NevadoMessageConsumer(Destination destination) {
        _destination = destination;
    }

    public String getMessageSelector() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MessageListener getMessageListener() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMessageListener(MessageListener messageListener) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Message receive() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Message receive(long l) throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Message receiveNoWait() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void close() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
