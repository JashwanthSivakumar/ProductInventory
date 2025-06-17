package com.example.productinventorysystem.controller;

import java.net.ResponseCache;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.productinventorysystem.exception.InputNotExist;
import com.example.productinventorysystem.model.Category;
import com.example.productinventorysystem.model.Product;
import com.example.productinventorysystem.model.Supplier;
import com.example.productinventorysystem.service.CategoryService;
import com.example.productinventorysystem.service.ProductService;
import com.example.productinventorysystem.service.SupplierService;

import jakarta.annotation.PostConstruct;

@RestController
public class AppController {
	
	@Autowired
	private ProductService productservice;
	
	@Autowired
	private SupplierService supplierservice;

	@Autowired
	private CategoryService categoryservice;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Welcome to Product Inventory System",HttpStatus.OK);
	}
	
	//Create a new product 
	
	@PostMapping("/product/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product saveProduct = productservice.saveProduct(product);
		if(saveProduct == null) {
			throw new InputNotExist("Product Not Exist "+saveProduct.getId());
		}
		return new ResponseEntity<Product>(saveProduct,HttpStatus.CREATED);
	}
	
	// fetch all product 
	
	@GetMapping("/product/all")
	public ResponseEntity<List<Product>> getAllProduct(@RequestBody Product product){
		List<Product> allProduct = productservice.displayAllProduct();
		if(allProduct == null) {
			throw new InputNotExist("Product Not Exist");
		}
		return new ResponseEntity<List<Product>>(allProduct,HttpStatus.CREATED);
	}
	
	// Fetch a product by ID
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product = productservice.findByProductId(id);
		if(product == null) {
			throw new InputNotExist("Product Id Not Exist "+id);
		}
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	// Update an existing product.
	
	@PutMapping("/product/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
		Product checkProduct = productservice.findByProductId(id);
		// set all new product
		if(checkProduct == null) {
			throw new InputNotExist("Product Id Not Exist To Update");
		}
		checkProduct.setName(product.getName());
		checkProduct.setPrice(product.getPrice());
		checkProduct.setDescription(product.getDescription());
		
		Supplier supplier = supplierservice.findBySupplierId(product.getSupplier().getId());
		checkProduct.setSupplier(supplier);
		supplierservice.saveSupplier(supplier);
		
		Category category = categoryservice.findByCategoryId(product.getCategory().getId());
		checkProduct.setCategory(category);
		categoryservice.saveCategory(category);
		
		productservice.saveProduct(checkProduct);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		Product product = productservice.findByProductId(id);
		if(product == null) {
			throw new InputNotExist("Given Product Id Not Exist "+id);
		}
		productservice.deleteProduct(id);
		return new ResponseEntity<String>("Product" + id + "Deleted Successfully",HttpStatus.OK);
	}
	
	// Fetch all categories.
	
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> getAllCategory(@RequestBody Category category){
		List<Category> allCategory = categoryservice.displayAllCategory();
		if(allCategory == null) {
			throw new InputNotExist("Category Not Exist");
		}
		return new ResponseEntity<List<Category>>(allCategory,HttpStatus.CREATED);
	}
	
	//Create a new category.
	@PostMapping("/category/save")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		Category saveCategory = categoryservice.saveCategory(category);
		if(saveCategory == null) {
			throw new InputNotExist("Category Not Exist "+saveCategory.getId());
		}
		return new ResponseEntity<Category>(saveCategory,HttpStatus.CREATED);
	}
	
	// Create a new supplier.
	@PostMapping("/supplier/save")
	public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier){
		Supplier saveSupplier = supplierservice.saveSupplier(supplier);
		if(saveSupplier == null) {
			throw new InputNotExist("Supplier Not Exist "+saveSupplier.getId());
		}
		return new ResponseEntity<Supplier>(saveSupplier,HttpStatus.CREATED);
	}
		
	//Fetch all suppliers.
	
	@GetMapping("/supplier/all")
	public ResponseEntity<List<Supplier>> getAllSupplier(@RequestBody Supplier supplier){
		List<Supplier> allSupplier = supplierservice.displayAllSupplier();
		if(allSupplier == null) {
			throw new InputNotExist("Supplier Not Exist");
		}
		return new ResponseEntity<List<Supplier>>(allSupplier,HttpStatus.CREATED);
	}
	
	//Fetch products filtered by category.
	
	@GetMapping("/product/category/{id}")
	public ResponseEntity<List<Product>> getByCategoryId(@PathVariable Long id){
		Category category = categoryservice.findByCategoryId(id);
		List<Product> ListOfProduct = productservice.displayAllProduct();
		List<Product> CategoryList = new ArrayList<Product>();
		for(Product productCheck : ListOfProduct) {
			if(productCheck.getCategory().getId() == category.getId()) {
				CategoryList.add(productCheck);
			}
		}
		return new ResponseEntity<List<Product>>(CategoryList,HttpStatus.OK);
	}
	
	//Fetch products filtered by supplier.
	
	@GetMapping("/product/supplier/{id}")
	public ResponseEntity<?> getBySupplierId(@PathVariable Long id){
		Supplier supplier = supplierservice.findBySupplierId(id);
		List<Product> ListOfProduct = productservice.displayAllProduct();
		List<Product> SupplierList = new ArrayList<Product>();
		for(Product productCheck : ListOfProduct) {
			if(productCheck.getSupplier().getId() == supplier.getId()) {
				SupplierList.add(productCheck);
			}
		}
		return new ResponseEntity<List<Product>>(SupplierList,HttpStatus.OK);
	}
}























