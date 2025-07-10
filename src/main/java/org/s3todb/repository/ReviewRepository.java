package org.s3todb.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.s3todb.entity.ReviewEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "SELECT * " +
            "FROM reviews WHERE hotel_id = :hotelId AND provider_id = :providerId AND review_id = :reviewId",
            nativeQuery = true)
    List<ReviewEntity> existsByHotelIdProviderIdReviewId(@Param("hotelId") Long hotelId,
                                                         @Param("providerId") Long providerId,
                                                         @Param("reviewId") Long reviewId);


}
