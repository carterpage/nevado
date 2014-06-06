package org.skyscreamer.nevado.jms.connector;

import javax.jms.JMSException;

/**
 * Factory for SQSConnector objects.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public interface SQSConnectorFactory {
    SQSConnector getInstance(NevadoConfiguration config) throws JMSException;

    @Deprecated
    SQSConnector getInstance(String awsAccessKey, String awsSecretKey) throws JMSException;

    @Deprecated
    SQSConnector getInstance(String awsAccessKey, String awsSecretKey, String awsSQSEndpoint,
        String awsSNSEndpoint) throws JMSException;
}
