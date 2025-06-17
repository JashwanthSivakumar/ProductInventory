package com.example.productinventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.productinventorysystem.model.Supplier;
import com.example.productinventorysystem.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierrepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierrepository.save(supplier);
	}
	
	public void deleteSupplier(Long id) {
		supplierrepository.deleteById(id);
	}
	
	public List<Supplier> displayAllSupplier(){
		return supplierrepository.findAll();
	}
	
	public Supplier findBySupplierId(Long id) {
		return supplierrepository.findById(id).get();
	}

}
