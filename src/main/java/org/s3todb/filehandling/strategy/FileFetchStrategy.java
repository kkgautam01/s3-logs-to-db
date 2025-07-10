package org.s3todb.filehandling.strategy;

import java.io.BufferedReader;
import java.util.List;

public interface FileFetchStrategy {
    List<String> fetchFileList();
    BufferedReader fetchFile(String key);
}
