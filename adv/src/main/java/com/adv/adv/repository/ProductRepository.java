
package com.adv.adv.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.adv.adv.model.*;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product deleteById(int id);
    Product findById(int id);
    Product getProductById(Long id);
    List<Product> findTop3ByOrderByIdDesc();
    List<Product> findAllByCategoryId(int categoryId);
    //TOP RATING PPRODUCTS
    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findAllByOrderByRatingDesc();
    //NEW PRODUCTS
    @Query("SELECT p FROM Product p ORDER BY p.created_at DESC")
    List<Product> findAllByOrderByCreatedAtDesc();
    List<Product> findTop3ByOrderByRatingDesc(); 
//COUNT
@Query("SELECT COUNT(p) FROM Product p")
int countProducts();


}