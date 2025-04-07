package com.example.CJV805.Assignment2.repository;

import com.example.CJV805.Assignment2.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findByType(String type);
    List<Media> findByTypeAndFeaturedTrue(String type);
    List<Media> findByFeatured(boolean featured);
    List<Media> findByGenre(String genre);
    List<Media> findByTitleContainingIgnoreCase(String title);
}
