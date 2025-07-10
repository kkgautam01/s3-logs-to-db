package org.s3todb.fileprocessing.adapter;

import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.HotelReviewDto;

import java.util.Map;

public interface ProviderAdapter {
    HotelReviewDto parse(String line, Provider provider, String fileType, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap);

}
