package org.s3todb.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.fileprocessing.dto.RatingsDto;
import org.s3todb.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RatingParser {
    public static List<RatingsDto> parse(JsonNode ratings, Map<String, Integer> ratingCategoryIdMap){
        List<RatingsDto> ratingsDtoList = new ArrayList<>();
        int cleanlinessId = ratingCategoryIdMap.get(Constants.CLEAN);
        int facilityId = ratingCategoryIdMap.get(Constants.FACILITY);
        int locationID = ratingCategoryIdMap.get(Constants.LOCATION);
        int serviceId = ratingCategoryIdMap.get(Constants.SERVICE);
        int valueForMoneyId = ratingCategoryIdMap.get(Constants.VALUE_FOR_MONEY);
        int roomComfortQualityId = ratingCategoryIdMap.get(Constants.ROOM_COMFORT_AND_QUALITY);

        if(ratings.has("Cleanliness"))
            ratingsDtoList.add(new RatingsDto(cleanlinessId,ratings.get("Cleanliness").asDouble()));

        if(ratings.has("Facilities"))
            ratingsDtoList.add(new RatingsDto(facilityId,ratings.get("Facilities").asDouble()));

        if(ratings.has("Location"))
            ratingsDtoList.add(new RatingsDto(locationID,ratings.get("Location").asDouble()));

        if(ratings.has("Service"))
            ratingsDtoList.add(new RatingsDto(serviceId,ratings.get("Service").asDouble()));

        if(ratings.has("Value for money"))
            ratingsDtoList.add(new RatingsDto(valueForMoneyId,ratings.get("Value for money").asDouble()));

        if(ratings.has("Room comfort and quality"))
            ratingsDtoList.add(new RatingsDto(roomComfortQualityId,ratings.get("Room comfort and quality").asDouble()));

        return ratingsDtoList;
    }
}
