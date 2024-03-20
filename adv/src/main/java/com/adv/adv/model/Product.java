package com.adv.adv.model;
import java.time.ZonedDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z\\s\\-_',.&()]+$", message = "Product name must contain only letters")

    private String name;
    private String photos;
    
    @NotBlank(message = "Descrption  cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z\\s\\-_',.&()]+$", message = "Product description name must contain only letters")
    private String description;
    @Min(value = 0, message = "Price must be non-negative")
    @NotNull(message = "Price cannot be null or empty")
    private Double price;

    @Min(value = 0, message = "Weight must be non-negative")
    @NotNull(message = "Weight cannot be null or empty")
    private Double weight;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @NotNull(message = "Rating cannot be null or empty")
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "category_id")
    
    private Category category;

    @ManyToOne
    @JoinColumn(name = "metal_id")
    private Metal metal;
  

    private boolean available = true; 
 @Column(nullable = false, updatable = false)
    private ZonedDateTime created_at;

    public ZonedDateTime getCreated_at() {
    return created_at;
}

public void setCreated_at(ZonedDateTime created_at) {
    this.created_at = created_at;
}

   

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public  Integer getRating() {
        return rating;
    }
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;
        return "/uploads/" + id + "/" + photos;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Metal getMetal() {
        return metal;
    }

    public void setMetal(Metal metal) {
        this.metal = metal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
