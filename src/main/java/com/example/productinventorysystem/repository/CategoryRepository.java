package com.example.productinventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productinventorysystem.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
