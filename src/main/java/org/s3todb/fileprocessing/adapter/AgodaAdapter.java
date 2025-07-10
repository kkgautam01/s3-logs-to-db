package org.s3todb.fileprocessing.adapter;

import org.springframework.stereotype.Component;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.dto.HotelReviewDto;
import org.s3todb.fileprocessing.parser.agoda.AgodaJsonParser;
import org.s3todb.util.Constants;
import org.s3todb.util.LogMessages;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AgodaAdapter implements ProviderAdapter{
    private static final Logger LOGGER = Logger.getLogger(AgodaAdapter.class.getName());

    public HotelReviewDto parse(String line, Provider provider, String fileType, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap) {
        // Extend this method to support multiple file formats
        switch (fileType.toLowerCase()) {
            case Constants.JSON, Constants.JL -> {
                AgodaJsonParser agodaJsonParser = new AgodaJsonParser();
                return agodaJsonParser.parse(line, provider, languageIdMap, ratingCategoryIdMap);
            }
            default -> {
                LOGGER.log(Level.SEVERE, LogMessages.UNSUPPORTED_FILE_TYPE);
            }

        }
        return null;
    }

}
