package com.learn.aws.basic.services.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;

import static com.learn.aws.basic.services.utils.AwsUtils.CREDENTIALS;
import static com.learn.aws.basic.services.utils.AwsUtils.DESTINATION_BUCKET_NAME;
import static com.learn.aws.basic.services.utils.AwsUtils.FILE;
import static com.learn.aws.basic.services.utils.AwsUtils.REGION;
import static com.learn.aws.basic.services.utils.AwsUtils.SOURCE_BUCKET_NAME;
import static com.learn.aws.basic.services.utils.AwsUtils.SOURCE_KEY;

public class S3Bucket {

    private static final AmazonS3 amazonS3 = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
            .withRegion(REGION)
            .build();

    private static boolean checkIfS3BucketExists() {
        return amazonS3.doesBucketExist(SOURCE_BUCKET_NAME);
    }

    private static boolean checkIfObjectExists() {
        return amazonS3.doesObjectExist(SOURCE_BUCKET_NAME, SOURCE_KEY);
    }

    private static void getObjectFromFileByBucketNameAndKey() {
        GetObjectRequest objectRequest = new GetObjectRequest(SOURCE_BUCKET_NAME, SOURCE_KEY);
        if (checkIfS3BucketExists() && checkIfObjectExists()) {
            amazonS3.getObject(objectRequest, FILE);
        }
    }

    private static void moveObjectFromSourceBucketToDestinationBucket() {
        amazonS3.copyObject(SOURCE_BUCKET_NAME, SOURCE_KEY, DESTINATION_BUCKET_NAME, SOURCE_KEY);
    }

    public static void main(String[] args) {
        getObjectFromFileByBucketNameAndKey();
        moveObjectFromSourceBucketToDestinationBucket();
    }
}
