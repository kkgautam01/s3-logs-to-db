package org.s3todb.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class LineMessage{
    Map<String, String> provider;
    Map<String, Integer> languageIdMap;
    Map<String, Integer> ratingCategoryIdMap;
    String line;
    String fileType;
    int retry;

//    public LineMessage(Map<String, String> providerMap, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap, String line, String fileType, int retry) {
//        this.provider = providerMap;
//        this.languageIdMap = languageIdMap;
//        this.ratingCategoryIdMap = ratingCategoryIdMap;
//        this.line = line;
//        this.fileType = fileType;
//        this.retry = retry;
//    }

    @JsonCreator
    public LineMessage(
            @JsonProperty("provider") Map<String, String> provider,
            @JsonProperty("languageIdMap") Map<String, Integer> languageIdMap,
            @JsonProperty("ratingCategoryIdMap") Map<String, Integer> ratingCategoryIdMap,
            @JsonProperty("line") String line,
            @JsonProperty("fileType") String fileType,
            @JsonProperty("retry") int retry
    ) {
        this.provider = provider;
        this.languageIdMap = languageIdMap;
        this.ratingCategoryIdMap = ratingCategoryIdMap;
        this.line = line;
        this.fileType = fileType;
        this.retry = retry;
    }
}
