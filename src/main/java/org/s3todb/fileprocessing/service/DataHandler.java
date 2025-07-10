package org.s3todb.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.s3todb.filehandling.service.Provider;
import org.s3todb.fileprocessing.adapter.ProviderAdapter;
import org.s3todb.fileprocessing.dto.HotelReviewDto;
import org.s3todb.kafka.Consumer;
import org.s3todb.util.LogMessages;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DataHandler {
    private static final Logger LOGGER = Logger.getLogger(DataHandler.class.getName());
    @Autowired
    StoreService storeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    Consumer consumer;
    public boolean saveContent(String line, ProviderAdapter adapter, Provider provider, String fileType,
                               Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap){

        consumer.read();
        HotelReviewDto hotelReviewDto = adapter.parse(line, provider, fileType, languageIdMap, ratingCategoryIdMap);
        if(hotelReviewDto != null){

            // check if log already processed
            if(!repositoryService.isLogExist(hotelReviewDto.getReviewDto().getHotelId(),
                    hotelReviewDto.getReviewDto().getProviderId(),
                    hotelReviewDto.getReviewDto().getHotelReviewId())) {

                return storeService.save(hotelReviewDto);
            }
        }else{
            LOGGER.log(Level.SEVERE, LogMessages.INVALID_JSON_FORMAT + " : Line : " + line);
        }
        return true;
    }
}
