package com.learn.aws.basic.services.sns;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Topic;

import java.util.List;

import static com.learn.aws.basic.services.utils.AwsUtils.CREDENTIALS;
import static com.learn.aws.basic.services.utils.AwsUtils.REGION;

public class Sns {
    private static final String TOPIC = "first-topic";
    private static final String TOPIC_ARN_EMAIL = "arn:aws:sns:us-east-1:857483371052:first-topic";
    private static final String EMAIL_SUBJECT = "This is test sns notification";
    private static final String EMAIL_MESSAGE = "This is message from Email notification";
    private static final CreateTopicRequest topicRequest = new CreateTopicRequest(TOPIC);
    private static final AmazonSNSClient amazonSNSClient = (AmazonSNSClient) AmazonSNSClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
            .withRegion(REGION)
            .build();

    private static void createTopic() {
        amazonSNSClient.createTopic(topicRequest);
    }

    private static List<Topic> getTopics() {
        return amazonSNSClient.listTopics().getTopics();
    }

    private static PublishResult publishMessageToTopic() {
        return amazonSNSClient.publish(TOPIC_ARN_EMAIL, EMAIL_MESSAGE, EMAIL_SUBJECT);
    }

    private static SubscribeResult subscribeToEmail() {
        SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN_EMAIL, "email", "some email");
        return amazonSNSClient.subscribe(subscribeRequest);
    }

    public static void main(String[] args) {
        createTopic();
        List<Topic> topics = getTopics();
        topics.forEach(System.out::println);
        SubscribeResult subscribeResult = subscribeToEmail();
        System.out.println(subscribeResult.toString());
        PublishResult publish = publishMessageToTopic();
        System.out.println(publish.toString());
    }
}
