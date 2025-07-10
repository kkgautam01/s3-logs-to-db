package org.s3todb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reviewers")
public class ReviewerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewerId;

    private Integer countryId;
    private String countryName;
    private String flagName;
    private Integer reviewGroupId;
    private String reviewGroupName;
    private Integer roomTypeId;
    private String roomTypeName;
    private Integer lengthOfStay;

}

