package org.s3todb.filehandling.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.filehandling.cloud.aws.AwsConfig;
import org.s3todb.filehandling.cloud.Cloud;
import org.s3todb.filehandling.cloud.CloudConfig;
import org.s3todb.filehandling.service.Provider;

public class AwsConfigAdapter implements CloudConfig {

    @Override
    public Cloud getCloudConfig(JsonNode jsonNode, Provider provider) {
        AwsConfig awsConfig = new AwsConfig(jsonNode);
        Cloud cloud = new Cloud();
        cloud.setSecretKey(awsConfig.getSecretKey());
        cloud.setBucket(awsConfig.getBucket());
        cloud.setAccessKey(awsConfig.getAccessKey());
        cloud.setPrefix(awsConfig.getPrefix());
        cloud.setType(awsConfig.getType());
        cloud.setRegion(awsConfig.getRegion());
        cloud.setPageSize(awsConfig.getPageSize());
        cloud.setProviderName(provider.getName());
        return cloud;
    }
}
