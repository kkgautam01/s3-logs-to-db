package org.s3todb.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.fileprocessing.dto.ReviewerDto;

public class ReviewerParser {
    public static ReviewerDto parse(JsonNode reviewer){
        ReviewerDto reviewerDto = new ReviewerDto();
        reviewerDto.setCountryId(reviewer.get("countryId").asInt());
        reviewerDto.setCountryName(reviewer.get("countryName").asText());
        reviewerDto.setFlagName(reviewer.get("flagName").asText());
        reviewerDto.setReviewGroupId(reviewer.get("reviewGroupId").asInt());
        reviewerDto.setReviewGroupName(reviewer.get("reviewGroupName").asText());
        reviewerDto.setRoomTypeId(reviewer.get("roomTypeId").asInt());
        reviewerDto.setRoomTypeName(reviewer.get("roomTypeName").asText());
        reviewerDto.setLengthOfStay(reviewer.get("lengthOfStay").asInt());
        return reviewerDto;
    }
}
