package org.s3todb.fileprocessing.service;

import org.springframework.stereotype.Component;
import org.s3todb.entity.*;
import org.s3todb.fileprocessing.dto.*;
import org.s3todb.fileprocessing.entityDTOMapper.*;

import java.util.List;

@Component
public class EntityDTOMapping {

    public HotelEntity mapHotel(HotelDto hotelDto){
        return HotelDTOMapper.mapToEntity(hotelDto);
    }

    public ReviewEntity mapReview(ReviewDto reviewDto, Long reviewerId){
         return ReviewsDTOMapper.mapToEntity(reviewDto, reviewerId);
    }

    public ReviewerEntity mapReviewer(ReviewerDto reviewerDto){
        return ReviewerDTOMapper.mapToEntity(reviewerDto);
    }

    public RatingSummaryEntity mapRatingSummary(RatingSummaryDto ratingSummaryDto){
        return  RatingSummaryDTOMapper.mapToEntity(ratingSummaryDto);
    }

    public List<RatingsEntity> mapRatings(List<RatingsDto> ratingsListDto, RatingSummaryEntity ratingSummaryEntity){
        return RatingsDTOMapper.mapToEntity(ratingsListDto, ratingSummaryEntity);
    }

}
