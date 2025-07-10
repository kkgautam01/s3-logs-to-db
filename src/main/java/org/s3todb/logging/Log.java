package org.s3todb.logging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {
    private String content;
    private Long providerId;
    private String fileName;
}
