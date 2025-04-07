package com.example.CJV805.Assignment2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "Media")
public class Media {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Synopsis is required")
    @Size(min = 10, max = 1000, message = "Synopsis must be between 10 and 1000 characters")
    private String synopsis;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotBlank(message = "Type is required (movie or tv)")
    private String type;

    @NotBlank(message = "Small poster URL is required")
    private String smallPoster;

    @NotBlank(message = "Large poster URL is required")
    private String largePoster;

    @NotNull(message = "Rent price is required")
    @PositiveOrZero(message = "Rent price must be 0 or greater")
    private Double rentPrice;

    @NotNull(message = "Purchase price is required")
    @PositiveOrZero(message = "Purchase price must be 0 or greater")
    private Double purchasePrice;

    private boolean featured;
    
}
