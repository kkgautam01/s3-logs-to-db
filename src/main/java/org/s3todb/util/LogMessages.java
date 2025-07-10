package org.s3todb.util;

public class LogMessages {

    private LogMessages() {}

    public static final String LOG_PROCESSED = "Logs Processed.";

    public static final String REVIEW_PROCESS_START = "Starting review processing...";
    public static final String REVIEW_PROCESS_END = "Completed review processing.";

    public static final String INVALID_JSON_FORMAT = "Invalid JSON format for payload.";
    public static final String PARSING_ERROR = "Error while parsing input JSON.";
    public static final String UNSUPPORTED_FILE_TYPE = "Unsupported file type.";
    public static final String COULD_NOT_PROCESS_THE_FILE = "Could not process the file.";
    public static final String INVALID_PROVIDER_CONFIG = "Invalid Provider Config.";
    public static final String ERROR_WHILE_STORING_LOG = "Error while saving log.";
    public static final String FILES_NOT_AVAILABLE = "Files not available in directory.";
    public static final String COULD_NOT_FETCH_FILES = "Could not fetch Files.";
    public static final String DIRECTORY_NOT_AVAILABLE = "Directory not available.";
    public static final String UNSUPPORTED_CLOUD_CONFIG = "Unsupported cloud config.";
    public static final String MESSAGE_COULD_NOT_SEND = "Could not send message.";
    public static final String PRODUCER_SENT_ERROR = "Producer sent error.";
    public static final String CONSUMER_RECEIVE_ERROR = "Consumer receive error.";
    public static final String CONSUMER_MESSAGE_PARSE_ERROR = "Consumer message parse error.";
    public static final String UNKNOW_TOPIC = "Unknown topic.";
}

