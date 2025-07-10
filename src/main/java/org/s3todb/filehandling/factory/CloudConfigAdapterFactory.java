package org.s3todb.filehandling.factory;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.s3todb.filehandling.adapter.AwsConfigAdapter;
import org.s3todb.filehandling.cloud.Cloud;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.util.Constants;
import org.s3todb.util.LogMessages;

@Service
public class CloudConfigAdapterFactory {

    public Cloud getConfig(String cloudType, JsonNode jsonNode, Provider provider){
        // extend class with gcp, azure, etc
        switch(cloudType){
            case Constants.AWS -> {
                AwsConfigAdapter awsConfigAdapter = new AwsConfigAdapter();
                return awsConfigAdapter.getCloudConfig(jsonNode, provider);
            }default -> {
                throw new IllegalArgumentException(LogMessages.UNSUPPORTED_CLOUD_CONFIG);
            }
        }
    }
}
