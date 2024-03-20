package com.adv.adv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @NotBlank(message = "Name cannot be null or empty")
      @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\s]+$", message = "Name cannot contain special characters and should have at least one alphabet")

    public String name;
    public Category() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
