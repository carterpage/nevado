package org.skyscreamer.nevado.jms.connector.amazonaws;

import org.apache.commons.lang.StringUtils;
import org.skyscreamer.nevado.jms.connector.AbstractSQSConnectorFactory;
import org.skyscreamer.nevado.jms.connector.NevadoConfiguration;
import org.skyscreamer.nevado.jms.connector.SQSConnector;

import javax.jms.JMSException;

/**
 * Connectory factory for Amazon AWS connector.
 *
 * @author Carter Page <carter@skyscreamer.org>
 */
public class AmazonAwsSQSConnectorFactory extends AbstractSQSConnectorFactory {
    @Deprecated
    protected boolean _useAsyncSend = false;

    @Override
    public AmazonAwsSQSConnector getInstance(NevadoConfiguration config) {
        AmazonAwsConfiguration awsConfig = AmazonAwsConfiguration.wrap(config);
        checkNotEmpty("accessKey", awsConfig.getAccessKey());
        checkNotEmpty("secretKey", awsConfig.getSecretKey());
        if (awsConfig.isSecure() == null) {
            awsConfig.setSecure(_isSecure);
        }
        if (awsConfig.getReceiveCheckIntervalMs() == null) {
            awsConfig.setReceiveCheckIntervalMs(_receiveCheckIntervalMs);
        }
        if (awsConfig.isUseAsyncSend() == null) {
            awsConfig.setUseAsyncSend(_useAsyncSend);
        }

        AmazonAwsSQSConnector amazonAwsSQSConnector = AmazonAwsSQSConnector.getInstance(awsConfig);

        return amazonAwsSQSConnector;
    }

    private void checkNotEmpty(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            throw new IllegalArgumentException("Required field \"" + fieldName + "\" missing.");
        }
    }

    @Deprecated
    @Override
    public AmazonAwsSQSConnector getInstance(String accessKey, String secretKey, String sqsEndpoint,
                                             String snsEndpoint) {
        AmazonAwsConfiguration config = new AmazonAwsConfiguration().withAccessKey(accessKey)
            .withSecretKey(secretKey).withSQSEndpoint(sqsEndpoint).withSNSEndpoint(snsEndpoint);
        return getInstance(config);
    }

    @Deprecated
    public void setUseAsyncSend(boolean useAsyncSend) {
        _useAsyncSend = useAsyncSend;
    }

    @Deprecated
    public boolean isUseAsyncSend() {
        return _useAsyncSend;
    }
}
