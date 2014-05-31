package org.skyscreamer.nevado.jms.facilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.nevado.jms.AbstractJMSTest;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Test MessageConsumer
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public class MessageConsumerTest extends AbstractJMSTest {
    protected ExecutorService executor;

    @Before
    public void setUp() throws IOException, JMSException {
        super.setUp();
        executor = Executors.newSingleThreadExecutor();
    }

    @After
    public void tearDown() throws JMSException {
        executor.shutdownNow();
        super.tearDown();
    }

    @Test(timeout = 5000)
    public void testReceive() throws Exception {
        long start = System.currentTimeMillis();
        Session session = createSession();
        Queue testQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(testQueue);
        Receive receive = new Receive(consumer);
        Future<Message> future = executor.submit(receive);

        boolean timeoutException = false;
        try {
            // This should hang indefinitely
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            timeoutException = true;
        }

        long totalTime = System.currentTimeMillis() - start;
        Assert.assertTrue("receive() returned without a message", timeoutException);
        _log.info("Test took " + totalTime + "ms");
        session.close();
    }

    @Test(timeout = 5000)
    public void testReceiveWait0() throws Exception {
        long start = System.currentTimeMillis();
        Session session = createSession();
        Queue testQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(testQueue);
        ReceiveWait receive = new ReceiveWait(consumer, 0);
        Future<Message> future = executor.submit(receive);

        boolean timeoutException = false;
        try {
            // This should hang indefinitely
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            timeoutException = true;
        }

        long totalTime = System.currentTimeMillis() - start;
        Assert.assertTrue("receive() returned without a message", timeoutException);
        _log.info("Test took " + totalTime + "ms");
    }

    @Test(timeout = 5000)
    public void testReceiveWait500() throws Exception {
        long start = System.currentTimeMillis();
        Session session = createSession();
        Queue testQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(testQueue);
        ReceiveWait receive = new ReceiveWait(consumer, 500);
        Future<Message> future = executor.submit(receive);

        boolean timeoutException = false;
        try {
            // This should return in half a second
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            timeoutException = true;
        }

        long totalTime = System.currentTimeMillis() - start;
        Assert.assertFalse("receive() timed out", timeoutException);
        _log.info("Test took " + totalTime + "ms");
    }

    @Test(timeout = 5000)
    public void testReceiveNoWait() throws Exception {
        long start = System.currentTimeMillis();
        Session session = createSession();
        Queue testQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(testQueue);
        ReceiveNoWait receive = new ReceiveNoWait(consumer);
        Future<Message> future = executor.submit(receive);

        boolean timeoutException = false;
        try {
            // This should return immediately
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            timeoutException = true;
        }

        long totalTime = System.currentTimeMillis() - start;
        Assert.assertFalse("receive() timed out", timeoutException);
        _log.info("Test took " + totalTime + "ms");
    }

    private class Receive implements Callable<Message> {
        private final MessageConsumer _consumer;

        public Receive(MessageConsumer consumer) {
            _consumer = consumer;
        }

        @Override
        public Message call() throws Exception {
            return _consumer.receive();
        }
    }

    private class ReceiveWait implements Callable<Message> {
        private final MessageConsumer _consumer;
        private final long _timeout;

        public ReceiveWait(MessageConsumer consumer, long timeout) {
            _consumer = consumer;
            _timeout = timeout;
        }

        @Override
        public Message call() throws Exception {
            return _consumer.receive(_timeout);
        }
    }

    private class ReceiveNoWait implements Callable<Message> {
        private final MessageConsumer _consumer;

        public ReceiveNoWait(MessageConsumer consumer) {
            _consumer = consumer;
        }

        @Override
        public Message call() throws Exception {
            return _consumer.receiveNoWait();
        }
    }
}
