package org.s3todb.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.s3todb.entity.*;
import org.s3todb.repository.*;

import java.util.List;

@Component
public class RepositoryService {
    @Autowired
    ProvidersRepository providersRepository;

    @Autowired
    HotelsRepository hotelsRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    RatingsRepository ratingsRepository;

    @Autowired
    RatingsCategoryRepository ratingsCategoryRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RatingSummaryRepository ratingSummaryRepository;

    @Autowired
    FilesRepository filesRepository;

    // Language
    public List<LanguageEntity> getAllLanguages(){
        return languageRepository.findAll();
    }

    // Hotel
    public HotelEntity saveHotel(HotelEntity hotelEntity){return hotelsRepository.save(hotelEntity);}

    // Rating Category
    public List<RatingCategoryEntity> getAllRatingCategory(){
        return ratingsCategoryRepository.findAll();
    }

    // Provider
    public List<ProviderEntity> getAllProviders(){
        return providersRepository.findAll();
    }

    // Reviews
    public ReviewEntity saveReviews(ReviewEntity reviewEntity){
        return reviewRepository.save(reviewEntity);
    }

    public boolean isLogExist(long hotelId, long providerId, long reviewId){
        List<ReviewEntity> reviewEntityList = reviewRepository.existsByHotelIdProviderIdReviewId(hotelId, providerId, reviewId);
        return !reviewEntityList.isEmpty();
    }

    // Reviewers
    public ReviewerEntity saveReviewer(ReviewerEntity reviewerEntity){return reviewerRepository.save(reviewerEntity);}

    // Ratings
    public void saveRatings(List<RatingsEntity> ratingsEntities){
        ratingsRepository.saveAll(ratingsEntities);
    }

    // Rating summary
    public RatingSummaryEntity saveRatings(RatingSummaryEntity ratingSummary){return ratingSummaryRepository.save(ratingSummary);}

    // Files
    public List<FilesEntity> getAllFiles(){
        return filesRepository.findAll();
    }
    public void saveFile(FilesEntity filesEntity){filesRepository.save(filesEntity);}
    public FilesEntity getFileByNameAndProviderId(String file, Long providerId){
        return filesRepository.getFileByNameAndProviderId(file,providerId);
    }


}
