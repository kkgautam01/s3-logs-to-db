package org.s3todb.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "rating_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoryName;

}
