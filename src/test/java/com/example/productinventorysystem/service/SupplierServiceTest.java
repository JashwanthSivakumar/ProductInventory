package com.example.productinventorysystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productinventorysystem.model.Supplier;
import com.example.productinventorysystem.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {
	
	@Mock
	private SupplierRepository supplierrepository;
	
	@InjectMocks
	private SupplierService supplierservice;
	
	private Supplier supplierOne;
	private Supplier supplierTwo;
	
	@BeforeEach
	void setUp() {
		supplierOne = new Supplier();
		supplierTwo = new Supplier();
		
		supplierOne.setId(101L);
		supplierOne.setName("Jash");
		supplierOne.setAddress("Vellore");
		
		supplierTwo.setId(102L);
		supplierTwo.setName("Manoj");
		supplierTwo.setAddress("Ramnad");
		
	}
	
	@Test
	public void testSaveSupplier() {
		when(supplierrepository.save(supplierOne)).thenReturn(supplierOne);
		Supplier supplier = supplierservice.saveSupplier(supplierOne);
		
		assertThat(supplier).isNotNull();
		assertThat(supplier.getName()).isEqualTo("Jash");
		verify(supplierrepository,times(1)).save(supplierOne);
	}
	
	@Test
	public void testSupplierById() {
		when(supplierrepository.findById(101L)).thenReturn(Optional.of(supplierOne));
		Supplier supplier = supplierservice.findBySupplierId(101L);
		
		assertThat(supplier).isNotNull();
		assertThat(supplier.getName()).isEqualTo("Jash");
		verify(supplierrepository,times(1)).findById(101L);
	}
	
	@Test
	public void testFetchAllSupplier() {
		when(supplierrepository.findAll()).thenReturn(Arrays.asList(supplierOne,supplierTwo));
		List<Supplier> supplier = supplierservice.displayAllSupplier();
		
		assertThat(supplier).isNotNull();
		assertThat(supplier).hasSize(2);
		verify(supplierrepository,times(1)).findAll();
	}
	
	@Test
	public void testDeleteSupplier() {
		doNothing().when(supplierrepository).deleteById(101L);
		supplierservice.deleteSupplier(101L);
		verify(supplierrepository,times(1)).deleteById(101L);
	}
}













