package org.s3todb.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.s3todb.entity.ReviewerEntity;
import org.s3todb.fileprocessing.dto.ReviewerDto;

@Component
public class ReviewerDTOMapper {

    public static ReviewerEntity mapToEntity(ReviewerDto reviewerDto){
        ReviewerEntity reviewerEntity = new ReviewerEntity();
        reviewerEntity.setReviewGroupId(reviewerDto.getReviewGroupId());
        reviewerEntity.setReviewGroupName(reviewerDto.getReviewGroupName());
        reviewerEntity.setCountryId(reviewerDto.getCountryId());
        reviewerEntity.setFlagName(reviewerDto.getFlagName());
        reviewerEntity.setCountryName(reviewerDto.getCountryName());
        reviewerEntity.setLengthOfStay(reviewerDto.getLengthOfStay());
        reviewerEntity.setRoomTypeId(reviewerDto.getRoomTypeId());
        reviewerEntity.setRoomTypeName(reviewerDto.getRoomTypeName());
        return reviewerEntity;
    }
}
