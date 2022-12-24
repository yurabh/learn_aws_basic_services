package com.learn.aws.basic.services.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import static com.learn.aws.basic.services.utils.AwsUtils.CREDENTIALS;
import static com.learn.aws.basic.services.utils.AwsUtils.REGION;

public class Sqs {
    private static final AmazonSQS amazonSQS = AmazonSQSClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
            .withRegion(REGION)
            .build();

    private static final String QUEUE = "myQueue";

    private static void createQueue() {
        amazonSQS.createQueue(QUEUE);
    }

    private static void sendMessageToTheQueue() {
        GetQueueUrlResult queueUrl = amazonSQS.getQueueUrl(QUEUE);
        amazonSQS.sendMessage(queueUrl.getQueueUrl(), "This is basic message sent to the queue");
    }

    private static void receiveMessageFromQueue() {
        GetQueueUrlResult queueUrl = amazonSQS.getQueueUrl(QUEUE);
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(queueUrl.getQueueUrl());
        receiveMessageResult.getMessages().forEach(message -> {
            System.out.println("Queue messageId: " + message.getMessageId());
            System.out.println("Queue message body:  " + message.getBody());
        });
    }

    private static void deleteMessageFromQueue() {
        GetQueueUrlResult queueUrl = amazonSQS.getQueueUrl(QUEUE);
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(queueUrl.getQueueUrl());
        receiveMessageResult.getMessages()
                .forEach(message -> System.out.println("Remove message: " + amazonSQS.deleteMessage(queueUrl.getQueueUrl(), message.getReceiptHandle())));
    }

    public static void main(String[] args) {
        createQueue();
        sendMessageToTheQueue();
        receiveMessageFromQueue();
        deleteMessageFromQueue();
    }
}
