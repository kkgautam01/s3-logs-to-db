package org.s3todb.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.HotelDto;

public class HotelParser {
    public static HotelDto parse(JsonNode node, Provider provider){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(node.get("hotelId").asLong());
        hotelDto.setProviderId(provider.getProviderId());
        hotelDto.setHotelName(node.get("hotelName").asText());

        return hotelDto;
    }
}
