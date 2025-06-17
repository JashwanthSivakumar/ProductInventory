package com.example.productinventorysystem.service;

import static org.junit.jupiter.api.Assertions.*;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productinventorysystem.model.Category;
import com.example.productinventorysystem.model.Supplier;
import com.example.productinventorysystem.repository.CategoryRepository;
import com.example.productinventorysystem.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

		@Mock
		private CategoryRepository categoryrepository;
		
		@InjectMocks
		private CategoryService categoryservice;
		
		private Category categoryOne;
		private Category categoryTwo;
		
		@BeforeEach
		void setUp() {
			categoryOne = new Category();
			categoryTwo = new Category();
			
			categoryOne.setId(101L);
			categoryOne.setName("Jash");
			
			categoryTwo.setId(102L);
			categoryTwo.setName("Manoj");
		}
		
		@Test
		public void testSaveCategory() {
			when(categoryrepository.save(categoryOne)).thenReturn(categoryOne);
			Category supplier = categoryservice.saveCategory(categoryOne);
			
			assertThat(supplier).isNotNull();
			assertThat(supplier.getName()).isEqualTo("Jash");
			verify(categoryrepository,times(1)).save(categoryOne);
		}
		
		@Test
		public void testCategoryById() {
			when(categoryrepository.findById(101L)).thenReturn(Optional.of(categoryOne));
			Category supplier = categoryservice.findByCategoryId(101L);
			
			assertThat(supplier).isNotNull();
			assertThat(supplier.getName()).isEqualTo("Jash");
			verify(categoryrepository,times(1)).findById(101L);
		}
		
		@Test
		public void testFetchAllCategory() {
			when(categoryrepository.findAll()).thenReturn(Arrays.asList(categoryOne,categoryTwo));
			List<Category> supplier = categoryservice.displayAllCategory();
			
			assertThat(supplier).isNotNull();
			assertThat(supplier).hasSize(2);
			verify(categoryrepository,times(1)).findAll();
		}
		
		@Test
		public void testDeleteCategory() {
			doNothing().when(categoryrepository).deleteById(101L);
			categoryservice.deleteCategory(101L);
			verify(categoryrepository,times(1)).deleteById(101L);
		}
}
