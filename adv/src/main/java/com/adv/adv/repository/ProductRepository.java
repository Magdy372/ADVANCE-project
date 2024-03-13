package com.adv.adv.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.adv.adv.model.*;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product deleteById(int id);
    Product findById(int id);
    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findAllByOrderByRatingDesc();
@Query("SELECT p FROM Product p ORDER BY p.created_at DESC")
List<Product> findAllByOrderByCreatedAtDesc();



}
