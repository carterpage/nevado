package org.skyscreamer.nevado.jms.connector.mock;

import org.skyscreamer.nevado.jms.connector.NevadoConfiguration;
import org.skyscreamer.nevado.jms.connector.SQSConnector;
import org.skyscreamer.nevado.jms.connector.SQSConnectorFactory;

import javax.jms.JMSException;
import javax.jms.ResourceAllocationException;

/**
 * Connector factory for the mock connector.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public class MockSQSConnectorFactory implements SQSConnectorFactory, ResettableMock {
    public static final String BAD_ENDPOINT_URL = "http://badurl";
    private MockSQSConnector _mockSQSConnector = new MockSQSConnector();

    @Override
    public SQSConnector getInstance(NevadoConfiguration config) {
        return _mockSQSConnector;
    }

    @Deprecated
    @Override
    public SQSConnector getInstance(String awsAccessKey, String awsSecretKey)
            throws ResourceAllocationException {
        return _mockSQSConnector;
    }

    @Deprecated
    @Override
    public SQSConnector getInstance(String awsAccessKey, String awsSecretKey, String awsSQSEndpoint,
        String awsSNSEndpoint) throws JMSException {

        if (BAD_ENDPOINT_URL.equals(awsSQSEndpoint) || BAD_ENDPOINT_URL.equals(awsSNSEndpoint)) {
            throw new ResourceAllocationException("Bad endpoint");
        }
        return _mockSQSConnector;
    }

    @Override
    public void reset() {
        _mockSQSConnector.reset();
    }
}
