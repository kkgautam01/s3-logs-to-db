package org.s3todb.fileprocessing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingsDto {

    public RatingsDto(int category, double rating) {
        this.category = category;
        this.rating = rating;
    }

    private int category;
    private double rating;
}
