package org.s3todb.fileprocessing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelReviewDto {
    HotelDto hotelDto;
    List<RatingsDto> ratingsDto;
    ReviewDto reviewDto;
    ReviewerDto reviewerDto;
    RatingSummaryDto ratingSummaryDto;
}
