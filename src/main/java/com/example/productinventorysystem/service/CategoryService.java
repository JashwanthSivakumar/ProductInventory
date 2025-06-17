package com.example.productinventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productinventorysystem.model.Category;
import com.example.productinventorysystem.model.Product;
import com.example.productinventorysystem.repository.CategoryRepository;
import com.example.productinventorysystem.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository categoryrepository;
	
	public Category saveCategory(Category category) {
		return categoryrepository.save(category);
	}
	
	public void deleteCategory(Long id) {
		categoryrepository.deleteById(id);
	}
	
	public List<Category> displayAllCategory(){
		return categoryrepository.findAll();
	}
	
	public Category findByCategoryId(Long id) {
		return categoryrepository.findById(id).get();
	}
}
