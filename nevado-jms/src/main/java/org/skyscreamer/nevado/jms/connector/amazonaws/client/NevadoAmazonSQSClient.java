package org.skyscreamer.nevado.jms.connector.amazonaws.client;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

public class NevadoAmazonSQSClient implements NevadoAmazonSQS {

    private AmazonSQS _client;

    public NevadoAmazonSQSClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        _client = new AmazonSQSClient(awsCredentials, clientConfiguration);
    }

    @Override
    public void setEndpoint(String endpoint) throws IllegalArgumentException {
        _client.setEndpoint(endpoint);
    }

    @Override
    public ListQueuesResult listQueues(ListQueuesRequest listQueuesRequest) throws AmazonServiceException,
            AmazonClientException {
        return _client.listQueues(listQueuesRequest);
    }

    @Override
    public void setQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest) throws AmazonServiceException,
            AmazonClientException {
        _client.setQueueAttributes(setQueueAttributesRequest);
    }

    @Override
    public void changeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest)
            throws AmazonServiceException, AmazonClientException {
        _client.changeMessageVisibility(changeMessageVisibilityRequest);
    }

    @Override
    public CreateQueueResult createQueue(CreateQueueRequest createQueueRequest) throws AmazonServiceException,
            AmazonClientException {
        return _client.createQueue(createQueueRequest);
    }

    @Override
    public GetQueueAttributesResult getQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest)
            throws AmazonServiceException, AmazonClientException {
        return _client.getQueueAttributes(getQueueAttributesRequest);
    }

    @Override
    public void deleteQueue(DeleteQueueRequest deleteQueueRequest) throws AmazonServiceException, AmazonClientException {
        _client.deleteQueue(deleteQueueRequest);
    }

    @Override
    public void deleteMessage(DeleteMessageRequest deleteMessageRequest) throws AmazonServiceException,
            AmazonClientException {
        _client.deleteMessage(deleteMessageRequest);
    }

    @Override
    public SendMessageResult sendMessage(SendMessageRequest sendMessageRequest) throws AmazonServiceException,
            AmazonClientException {
        return _client.sendMessage(sendMessageRequest);
    }

    @Override
    public ReceiveMessageResult receiveMessage(ReceiveMessageRequest receiveMessageRequest)
            throws AmazonServiceException, AmazonClientException {
        return _client.receiveMessage(receiveMessageRequest);
    }

    @Override
    public ListQueuesResult listQueues() throws AmazonServiceException, AmazonClientException {
        return _client.listQueues();
    }

}
