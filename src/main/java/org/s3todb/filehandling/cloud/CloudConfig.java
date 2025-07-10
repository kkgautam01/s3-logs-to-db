package org.s3todb.filehandling.cloud;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.filehandling.service.Provider;

public interface CloudConfig {
    public Cloud getCloudConfig(JsonNode jsonNode, Provider provider);
}
