package com.example.CJV805.Assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CJV805.Assignment2.repository.MediaRepository;
import com.example.CJV805.Assignment2.model.Media;
import java.util.List;
@Service
public class MediaService {
    

    @Autowired
    private MediaRepository mediaRepository;

    public Media createMedia (Media media) {
        return mediaRepository.save(media);
    }

    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public List <Media> getAllMovies() {
        return mediaRepository.findByType("movie");
    }
    public List <Media> getAllTVShows() {
        return mediaRepository.findByType("tv");
    }
    public List <Media> getFeaturedMedia(String type) {
        return mediaRepository.findByTypeAndFeaturedTrue(type);
    }

    public List <Media> searchByTitle (String title) {
        return mediaRepository.findByTitleContainingIgnoreCase(title);
    }

    public List <Media> searchByGenre (String genre) {
        return mediaRepository.findByGenre(genre);
    }

    public Media getMediaById(String id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media not found with ID: " + id));
    }

    public Media updateMedia(String id, Media updatedMedia) {
        Media existing = getMediaById(id);

        existing.setTitle(updatedMedia.getTitle());
        existing.setSynopsis(updatedMedia.getSynopsis());
        existing.setGenre(updatedMedia.getGenre());
        existing.setType(updatedMedia.getType());
        existing.setSmallPoster(updatedMedia.getSmallPoster());
        existing.setLargePoster(updatedMedia.getLargePoster());
        existing.setRentPrice(updatedMedia.getRentPrice());
        existing.setPurchasePrice(updatedMedia.getPurchasePrice());
        existing.setFeatured(updatedMedia.isFeatured());

        return mediaRepository.save(existing);
    }

    public void deleteMedia(String id) {
        if (!mediaRepository.existsById(id)) {
            throw new IllegalArgumentException("Media with ID " + id + " does not exist");
        }
        mediaRepository.deleteById(id);
    }

}
