package com.learn.aws.basic.services.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.InputStream;

public class S3Bucket {
    private static final String REGION = "us-east-1";
    private static final String bucketName = "number-phone-bucket";
    private static final String key = "phones/phones-lists.txt";
    private static final String accessKey = "";
    private static final String secretKey = "";
    private static final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    private static final File file = new File("");
    private static final AmazonS3 amazonS3 = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(REGION)
            .build();

    private static boolean checkIfS3BucketExists() {
        return amazonS3.doesBucketExist(bucketName);
    }

    private static boolean checkIfObjectExists() {
        return amazonS3.doesObjectExist(bucketName, key);
    }

    private static void getContentFromFileByBucketNameAndKey() {
        GetObjectRequest objectRequest = new GetObjectRequest(bucketName, key);
        if (checkIfS3BucketExists() && checkIfObjectExists()) {
            amazonS3.getObject(objectRequest, file);

        }
    }

    public static void main(String[] args) {
        getContentFromFileByBucketNameAndKey();
        S3Object s3Object = amazonS3.getObject(bucketName, key);
        InputStream inputStream = s3Object.getObjectContent();
    }
}
