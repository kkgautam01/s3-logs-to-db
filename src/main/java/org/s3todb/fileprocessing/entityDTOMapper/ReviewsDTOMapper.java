package org.s3todb.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.s3todb.entity.ReviewEntity;
import org.s3todb.fileprocessing.dto.ReviewDto;

@Component
public class ReviewsDTOMapper {
    public static ReviewEntity mapToEntity(ReviewDto reviewDto, Long reviewerId){
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewerId(reviewerId);
        reviewEntity.setReviewComment(reviewDto.getReviewComments());
        reviewEntity.setCheckinMonthYear(reviewDto.getCheckInDate());
        reviewEntity.setReviewDate(reviewDto.getReviewDate());
        reviewEntity.setHotelId(reviewDto.getHotelId());
        reviewEntity.setReviewTitle(reviewDto.getReviewTitle());
        reviewEntity.setReviewId(reviewDto.getHotelReviewId());
        reviewEntity.setProviderId(reviewDto.getProviderId());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setTranslatedFrom(reviewDto.getTranslateSource());
        reviewEntity.setTranslatedTo(reviewDto.getTranslateTarget());

        return reviewEntity;
    }
}
