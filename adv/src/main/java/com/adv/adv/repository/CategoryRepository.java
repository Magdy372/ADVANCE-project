package com.adv.adv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adv.adv.model.*;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category deleteById(int id);


     Category findById(int id);
}