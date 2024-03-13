package com.adv.adv.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adv.adv.model.*;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product deleteById(int id);
    Product findById(int id);
    List<Product> findByType(String type);

    //  @Query(value = "SELECT p FROM Product p ORDER BY p.id DESC")
    //  List<Product> findTop3ByOrderByCreatedDateDesc();


}
