package com.diling.repository;

import com.diling.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM PRODUCT WHERE DATE LIKE '?1'", nativeQuery = true)
    List<Product> findProductByDate(String date);
}
