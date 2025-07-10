package org.s3todb.fileprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingSummaryDto {
    private Long hotelId;
    private Long providerId;
    private double totalRating;
    private int reviewCount;
}
