package com.example.CJV805.Assignment2.controller;

import com.example.CJV805.Assignment2.model.Media;
import com.example.CJV805.Assignment2.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    // Create a new movie or TV show
    @PostMapping
    public ResponseEntity<?> createMedia(@RequestBody Media media) {
        Media created = mediaService.createMedia(media);
        return ResponseEntity.status(201).body(created);
    }

    // Get all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Media>> getAllMovies() {
        return ResponseEntity.ok(mediaService.getAllMovies());
    }

    // Get all TV shows
    @GetMapping("/tv")
    public ResponseEntity<List<Media>> getAllTVShows() {
        return ResponseEntity.ok(mediaService.getAllTVShows());
    }

    // Get featured media (movie or tv) — must pass ?type=movie or ?type=tv
    @GetMapping("/featured")
    public ResponseEntity<List<Media>> getFeaturedMedia(@RequestParam String type) {
        return ResponseEntity.ok(mediaService.getFeaturedMedia(type));
    }

    // Search by title (case-insensitive)
    @GetMapping("/search")
    public ResponseEntity<List<Media>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(mediaService.searchByTitle(title));
    }

    // Get specific media by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMediaById(@PathVariable String id) {
        try {
            Media media = mediaService.getMediaById(id);
            return ResponseEntity.ok(media);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update media by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedia(@PathVariable String id, @RequestBody Media updatedMedia) {
        try {
            Media media = mediaService.updateMedia(id, updatedMedia);
            return ResponseEntity.ok(media);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete media by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable String id) {
        try {
            mediaService.deleteMedia(id);
            return ResponseEntity.ok("Media deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}