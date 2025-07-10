package org.s3todb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Getter
@Setter
public class FilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long providerId;
    private String bucket;
    private String file;
    private boolean status;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

}