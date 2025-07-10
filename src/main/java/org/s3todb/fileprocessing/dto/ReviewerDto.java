package org.s3todb.fileprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewerDto {
    private Integer countryId;
    private String countryName;
    private String flagName;
    private Integer reviewGroupId;
    private String reviewGroupName;
    private Integer roomTypeId;
    private String roomTypeName;
    private Integer lengthOfStay;
}
