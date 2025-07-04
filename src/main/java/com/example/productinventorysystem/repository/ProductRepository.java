package com.example.productinventorysystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.productinventorysystem.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
