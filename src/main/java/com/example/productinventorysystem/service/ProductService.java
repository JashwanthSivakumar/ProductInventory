package com.example.productinventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productinventorysystem.model.Product;
import com.example.productinventorysystem.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productrepository;
	
	public Product saveProduct(Product product) {
		return productrepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productrepository.deleteById(id);
	}
	
	public List<Product> displayAllProduct(){
		return productrepository.findAll();
	}
	
	public Product findByProductId(Long id) {
		return productrepository.findById(id).get();
	}

}
