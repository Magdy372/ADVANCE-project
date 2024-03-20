package com.adv.adv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.validation.constraints.NotBlank;
>>>>>>> 9cf58e83346c55214ef6f7cb996e2a146970d377
import jakarta.validation.constraints.Pattern;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
<<<<<<< HEAD
       
    @Pattern(regexp = "^[a-zA-Z\\s\\-_',.&()]+$", message = "Category name must contain only letters")
=======
    @NotBlank(message = "Name cannot be null or empty")
      @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\\s]+$", message = "Name cannot contain special characters and should have at least one alphabet")
>>>>>>> 9cf58e83346c55214ef6f7cb996e2a146970d377
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
