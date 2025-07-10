package org.s3todb.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.HotelDto;
import org.s3todb.fileprocessing.dto.RatingSummaryDto;

public class RatingSummaryParser {
    public static RatingSummaryDto parse(JsonNode node, Provider provider, HotelDto hotelDto){
        RatingSummaryDto ratingSummaryDto = new RatingSummaryDto();
        ratingSummaryDto.setHotelId(hotelDto.getHotelId());
        ratingSummaryDto.setProviderId(provider.getProviderId());
        ratingSummaryDto.setTotalRating(node.get("overallScore").asDouble());
        ratingSummaryDto.setReviewCount(node.get("reviewCount").asInt());

        return ratingSummaryDto;
    }
}
