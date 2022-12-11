package com.learn.aws.basic.services.rds;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.rds.model.DBInstance;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;

import java.util.List;

import static com.learn.aws.basic.services.utils.AwsUtils.CREDENTIALS;
import static com.learn.aws.basic.services.utils.AwsUtils.REGION;

public class RdsMySql {
    private static final AmazonRDS amazonRDS = AmazonRDSClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS))
            .withRegion(REGION).build();

    public static void main(String[] args) {
        DescribeDBInstancesResult dbInstancesResult = amazonRDS.describeDBInstances();

        List<DBInstance> dbInstances = dbInstancesResult.getDBInstances();
        for (DBInstance dbInstance : dbInstances) {
            System.out.println("Instance: " + dbInstance.getDBName());
        }
    }
}
