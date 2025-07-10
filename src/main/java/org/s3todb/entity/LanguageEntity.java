package org.s3todb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language")
@Setter
@Getter
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String language;
    private String description;

}

