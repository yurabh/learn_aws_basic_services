package com.learn.aws.basic.services.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import java.io.File;

public final class AwsUtils {
    private AwsUtils() {
    }

    public static final String SOURCE_BUCKET_NAME = "number-phone-bucket";
    public static final String DESTINATION_BUCKET_NAME = "destionation-number-phone-bucket";
    public static final String SOURCE_KEY = "phones/phones-lists.txt";
    public static final String REGION = "us-east-1";
    public static final String SOURCE_ACCESS_KEY = "some key";
    public static final String SOURCE_SECRET_KEY = "some key";
    public static final AWSCredentials CREDENTIALS = new BasicAWSCredentials(SOURCE_ACCESS_KEY, SOURCE_SECRET_KEY);
    public static final File FILE = new File("path to file");
}
