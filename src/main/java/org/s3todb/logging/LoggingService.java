package org.s3todb.logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private static final Logger RETRY_LOG = LoggerFactory.getLogger("org.s3todb.logging");

    public void retryLogs(String line, Long providerId, String fileName){
        Log log = new Log();
        log.setContent(line);
        log.setProviderId(providerId);
        log.setFileName(fileName);

        this.write(log);
    }
    public void write(Log log){
        String stringBuilder = log.getProviderId() + "\t" +
                log.getFileName() + "\t" +
                log.getContent();

        RETRY_LOG.info(stringBuilder);
    }

}
