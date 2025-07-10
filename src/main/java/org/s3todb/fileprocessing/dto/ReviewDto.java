package org.s3todb.fileprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long hotelReviewId;
    private Long hotelId;
    private Long providerId;

    private Double rating;
    private String reviewComments;
    private String reviewTitle;
    private String reviewDate;
    private int translateSource;
    private int translateTarget;
    private String checkInDate;
}
