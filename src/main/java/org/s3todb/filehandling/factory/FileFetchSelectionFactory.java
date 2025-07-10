package org.s3todb.filehandling.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.s3todb.filehandling.cloud.aws.AwsClient;
import org.s3todb.filehandling.cloud.Cloud;
import org.s3todb.filehandling.strategy.AwsFileFetchStrategy;
import org.s3todb.filehandling.strategy.FileFetchStrategy;
import org.s3todb.util.Constants;
import org.s3todb.util.LogMessages;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class FileFetchSelectionFactory {
    @Autowired
    AwsClient awsClient;
    public FileFetchStrategy getStrategy(Cloud cloudConfig){
        // Extend for different cloud providers. such as Azure, Gcp
        return switch (cloudConfig.getType().toLowerCase()) {
            case Constants.AWS -> {
                S3Client s3Client = awsClient.getClient(cloudConfig);
                yield new AwsFileFetchStrategy(s3Client, cloudConfig);
            }
            default -> throw new IllegalArgumentException(LogMessages.UNSUPPORTED_CLOUD_CONFIG);
        };
    }
}
