package org.s3todb.filehandling.cloud.aws;

import org.springframework.stereotype.Component;
import org.s3todb.filehandling.cloud.Cloud;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class AwsClient {
  
    public S3Client getClient(Cloud cloudConfig) {
        AwsCredentials credentials = AwsBasicCredentials.create(cloudConfig.getAccessKey(),
                cloudConfig.getSecretKey());

        try {
            return S3Client
                    .builder()
                    .region(Region.of(cloudConfig.getRegion()))
                    .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .build();
        }catch(Exception e){
            return null;
        }
    }
}
