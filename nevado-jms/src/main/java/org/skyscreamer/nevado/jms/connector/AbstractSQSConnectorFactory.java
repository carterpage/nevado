package org.skyscreamer.nevado.jms.connector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;

/**
 * Abstract implementation of factory for SQSConnector objects.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public abstract class AbstractSQSConnectorFactory implements SQSConnectorFactory {
    protected final Log _log = LogFactory.getLog(getClass());

    @Deprecated
    protected boolean _isSecure = true;
    @Deprecated
    protected long _receiveCheckIntervalMs = NevadoConfiguration.DEFAULT_RECEIVE_CHECK_INTERVAL_MS;

    @Deprecated
    @Override
    public abstract SQSConnector getInstance(String awsAccessKey, String awsSecretKey,
        String awsSQSEndpoint, String awsSNSEndpoint) throws JMSException;

    @Deprecated
    @Override
    public SQSConnector getInstance(String awsAccessKey, String awsSecretKey) throws JMSException {
        return getInstance(awsAccessKey, awsSecretKey, null, null);
    }

    @Deprecated
    public void setSecure(boolean secure) {
        _isSecure = secure;
    }

    @Deprecated
    public void setReceiveCheckIntervalMs(long receiveCheckIntervalMs) {
        _receiveCheckIntervalMs = receiveCheckIntervalMs;
    }
}
