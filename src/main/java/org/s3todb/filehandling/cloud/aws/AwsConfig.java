package org.s3todb.filehandling.cloud.aws;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.logging.Logger;

@Getter
@Setter
public class AwsConfig {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    String bucket;
    String region;
    String accessKey;
    String secretKey;
    int pageSize;
    String prefix;
    String type;

    public AwsConfig(JsonNode jsonNode) {
        this.bucket = jsonNode.get("bucket").asText();
        this.region = jsonNode.get("region").asText();
        this.accessKey = jsonNode.get("access_key").asText();
        this.secretKey = jsonNode.get("secret_key").asText();
        this.pageSize = jsonNode.get("page_size").asInt();
        this.prefix = jsonNode.get("prefix").asText();
        this.type = jsonNode.get("type").asText();
    }


}
