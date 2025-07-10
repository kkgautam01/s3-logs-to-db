package org.s3todb.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.s3todb.entity.*;
import org.s3todb.fileprocessing.dto.HotelReviewDto;
import org.s3todb.util.LogMessages;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StoreService {

    EntityDTOMapping entityDTOMappingService;
    private static final Logger LOGGER = Logger.getLogger(StoreService.class.getName());

    @Autowired
    private RepositoryService storeReviewsService;

    @Transactional
    public boolean save(HotelReviewDto hotelReviewDto){
        this.entityDTOMappingService = new EntityDTOMapping();
        EntityDTOMapping entityDTOMapping = new EntityDTOMapping();

        try {

            // save hotel
            HotelEntity hotelEntity = entityDTOMapping.mapHotel(hotelReviewDto.getHotelDto());
            HotelEntity newHotel = storeReviewsService.saveHotel(hotelEntity);

            // save reviewer
            ReviewerEntity reviewerEntity = entityDTOMapping.mapReviewer(hotelReviewDto.getReviewerDto());
            ReviewerEntity newReviewer = storeReviewsService.saveReviewer(reviewerEntity);

            // save review
            ReviewEntity reviewEntity = entityDTOMapping.mapReview(hotelReviewDto.getReviewDto(), newReviewer.getReviewerId());
            ReviewEntity newReview = storeReviewsService.saveReviews(reviewEntity);

            // save rating summary
            RatingSummaryEntity ratingSummaryEntity = entityDTOMapping.mapRatingSummary(hotelReviewDto.getRatingSummaryDto());
            RatingSummaryEntity newRatingSummary = storeReviewsService.saveRatings(ratingSummaryEntity);

            // save rating
            List<RatingsEntity> ratingsEntity = entityDTOMapping.mapRatings(hotelReviewDto.getRatingsDto(), newRatingSummary);
            storeReviewsService.saveRatings(ratingsEntity);

            return true;

        }catch(Exception e){
            LOGGER.log(Level.SEVERE, LogMessages.ERROR_WHILE_STORING_LOG + " :: " + e.getMessage());
        }

        return false;

    }

}
