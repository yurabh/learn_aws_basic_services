package com.learn.aws.basic.services.dynamodb;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.learn.aws.basic.services.utils.AwsUtils;

import java.util.Objects;

public class DynamoDb {
    private static final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(AwsUtils.CREDENTIALS))
            .build();
    private static final DynamoDB db = new DynamoDB(amazonDynamoDB);

    private static void addUser(User user) {
        Table table = db.getTable("user");
        table.putItem(new Item().withPrimaryKey("userId", user.getUserId())
                .with("firstName", user.getFirstName())
                .with("lastName", user.getLastName()));
    }

    private static User getUser(final String key) {
        Table table = db.getTable("user");
        GetItemSpec userItemSpec = new GetItemSpec().withPrimaryKey("userId", key);
        Item item = table.getItem(userItemSpec);
        return retrieveUser(item);
    }

    private static User retrieveUser(Item item) {
        if (Objects.nonNull(item)) {
            User user = new User();
            user.setUserId(item.get("userId").toString());
            user.setFirstName(item.get("firstName").toString());
            user.setLastName(item.get("lastName").toString());
            return user;
        }
        return null;
    }

    public static void main(String[] args) {
        addUser(new User("3", "Yura", "Bahlay"));
        User user = getUser("3");
        System.out.println(user);
    }
}
