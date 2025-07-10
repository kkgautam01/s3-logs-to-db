package org.s3todb.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.s3todb.entity.RatingSummaryEntity;
import org.s3todb.entity.RatingsEntity;
import org.s3todb.fileprocessing.dto.RatingsDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingsDTOMapper {
    public static List<RatingsEntity> mapToEntity(List<RatingsDto> ratingsListDto, RatingSummaryEntity ratingSummaryEntity){
        List<RatingsEntity> ratingsEntityList = new ArrayList<>();

        for(RatingsDto rating : ratingsListDto){
            RatingsEntity ratingsEntity = new RatingsEntity();
            ratingsEntity.setRatingSummaryId(ratingSummaryEntity.getId());
            ratingsEntity.setHotelId(ratingSummaryEntity.getHotelId());
            ratingsEntity.setProviderId(ratingSummaryEntity.getProviderId());
            ratingsEntity.setCategory(rating.getCategory());
            ratingsEntity.setRating(rating.getRating());
            ratingsEntityList.add(ratingsEntity);
        }


        return ratingsEntityList;
    }
}
