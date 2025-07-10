package org.s3todb.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.HotelDto;
import org.s3todb.fileprocessing.dto.ReviewDto;
import org.s3todb.util.Constants;

import java.util.Map;

public class ReviewParser {
    public static ReviewDto parse(JsonNode reviews, HotelDto hotel, Provider provider, Map<String, Integer> languageIdMap){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setHotelReviewId(reviews.get("hotelReviewId").asLong());
        reviewDto.setHotelId(hotel.getHotelId());
        reviewDto.setProviderId(provider.getProviderId());

        reviewDto.setRating(reviews.get("rating").asDouble());
        reviewDto.setReviewComments(reviews.get("reviewComments").asText());
        reviewDto.setReviewTitle(reviews.get("reviewTitle").asText());
        reviewDto.setReviewDate(reviews.get("reviewDate").asText());
        reviewDto.setCheckInDate(reviews.get("checkInDateMonthAndYear").asText());

        String translateSource = reviews.get("translateSource").asText();
        String translateTarget = reviews.get("translateSource").asText();

        // setting default value
        int translateSourceId = languageIdMap.get(Constants.ENGLISH.toLowerCase());
        int translateTargetId = languageIdMap.get(Constants.ENGLISH.toLowerCase());

        // update language Id
        if(languageIdMap.containsKey(translateSource.toLowerCase()))
            translateSourceId = languageIdMap.get(translateSource.toLowerCase());

        if(languageIdMap.containsKey(translateTarget.toLowerCase()))
            translateTargetId = languageIdMap.get(translateTarget.toLowerCase());

        reviewDto.setTranslateSource(translateSourceId);
        reviewDto.setTranslateTarget(translateTargetId);

        return reviewDto;
    }
}
