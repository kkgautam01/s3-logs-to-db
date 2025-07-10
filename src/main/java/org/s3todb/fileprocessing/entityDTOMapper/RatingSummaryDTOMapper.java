package org.s3todb.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.s3todb.entity.RatingSummaryEntity;
import org.s3todb.fileprocessing.dto.RatingSummaryDto;

@Component
public class RatingSummaryDTOMapper {
    public static RatingSummaryEntity mapToEntity(RatingSummaryDto ratingSummaryDto){
        RatingSummaryEntity ratingSummaryEntity = new RatingSummaryEntity();
        ratingSummaryEntity.setHotelId(ratingSummaryDto.getHotelId());
        ratingSummaryEntity.setProviderId(ratingSummaryDto.getProviderId());
        ratingSummaryEntity.setReviewCount(ratingSummaryDto.getReviewCount());
        ratingSummaryEntity.setTotalScore(ratingSummaryDto.getTotalRating());

        return ratingSummaryEntity;
    }
}
