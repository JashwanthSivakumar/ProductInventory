package com.example.productinventorysystem.service;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productinventorysystem.model.Product;
import com.example.productinventorysystem.repository.ProductRepository;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {		
		@Mock
		private ProductRepository productrepository;
		
		@InjectMocks
		private ProductService productservice;
		
		private Product productOne;
		private Product productTwo;
		
		@BeforeEach
		void setUp() {
			productOne = new Product();
			productTwo = new Product();
			
			productOne.setId(201L);
			productOne.setName("Jash");
			productOne.setPrice(1000);
			productOne.setDescription("Good");
			
			productTwo.setId(202L);
			productTwo.setName("Manoj");
			productTwo.setPrice(900);
			productTwo.setDescription("Bad");
			
		}
		
		@Test
		public void testSaveProduct() {
			when(productrepository.save(productOne)).thenReturn(productOne);
			Product product = productservice.saveProduct(productOne);
			
			assertThat(product).isNotNull();
			assertThat(product.getName()).isEqualTo("Jash");
			verify(productrepository,times(1)).save(productOne);
		}
		
		@Test
		public void testProductById() {
			when(productrepository.findById(201L)).thenReturn(Optional.of(productOne));
			Product product = productservice.findByProductId(201L);
			
			assertThat(product).isNotNull();
			assertThat(product.getName()).isEqualTo("Jash");
			verify(productrepository,times(1)).findById(201L);
		}
		
		@Test
		public void testFetchAllSupplier() {
			when(productrepository.findAll()).thenReturn(Arrays.asList(productOne,productTwo));
			List<Product> product = productservice.displayAllProduct();
			
			assertThat(product).isNotNull();
			assertThat(product).hasSize(2);
			verify(productrepository,times(1)).findAll();
		}
		
		@Test
		public void testDeleteSupplier() {
			doNothing().when(productrepository).deleteById(201L);
			productservice.deleteProduct(201L);
			verify(productrepository,times(1)).deleteById(201L);
		}
	}
