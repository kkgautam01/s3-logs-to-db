package org.s3todb.fileprocessing.parser.agoda;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.*;
import org.s3todb.fileprocessing.parser.agoda.jsonParser.*;
import org.s3todb.util.LogMessages;
import org.s3todb.util.Utilities;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AgodaJsonParser {
    private static final Logger LOGGER = Logger.getLogger(AgodaJsonParser.class.getName());

    Provider provider;
    public HotelReviewDto parse(String line, Provider provider, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap) {
        Utilities utils = new Utilities();
        this.provider = provider;
        HotelReviewDto hotelReviewDto = new HotelReviewDto();
        try {

            // generate Json Object
            JsonNode node = utils.getFileAsJsonNode(line);
            JsonNode review = node.get("comment");
            JsonNode reviewer = review.get("reviewerInfo");
            JsonNode overallByProviders = node.get("overallByProviders").get(0);
            JsonNode ratings = overallByProviders.get("grades");

            // Set Json object to DTO
            HotelDto hotelDto = HotelParser.parse(node, provider);
            List<RatingsDto> ratingsListDto = RatingParser.parse(ratings,ratingCategoryIdMap);
            ReviewDto reviewDto = ReviewParser.parse(review, hotelDto, provider, languageIdMap);
            ReviewerDto reviewerDto = ReviewerParser.parse(reviewer);
            RatingSummaryDto ratingSummaryDto = RatingSummaryParser.parse(overallByProviders, provider, hotelDto);

            // Set All DTO under single Dto
            hotelReviewDto.setRatingsDto(ratingsListDto);
            hotelReviewDto.setReviewerDto(reviewerDto);
            hotelReviewDto.setHotelDto(hotelDto);
            hotelReviewDto.setReviewDto(reviewDto);
            hotelReviewDto.setRatingSummaryDto(ratingSummaryDto);
            return hotelReviewDto;


        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, LogMessages.PARSING_ERROR + " :: " + e.fillInStackTrace() );
        }

        return null;
    }








}

