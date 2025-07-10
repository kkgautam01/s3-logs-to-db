package org.s3todb.filehandling.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cloud {
    String bucket;
    String region;
    String accessKey;
    String secretKey;
    int pageSize;
    String prefix;
    String type;
    String providerName;

    // can extend this class further to support Azure, gcp, etc..

}
