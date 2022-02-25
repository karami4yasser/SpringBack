package com.example.demo.repository;
import  com.example.demo.model.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Category;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	
	@Query(nativeQuery=true, value="SELECT * FROM PRODUCTT WHERE stock != 0 ")
	List<Product> findByStockNot();
	List<Product> findByCategory(String category,Pageable pageable);
	List<Product> findAllByOrderByPriceDesc(Pageable pageable);
	//@Query(nativeQuery=true, value="SELECT * FROM producttest WHERE title = ?1 ")
	List<Product> findByTitle(String title);
	
	//@Query("select count(*) from PRODUCTT p where p.category = ?1" )
	Long countBycategory(String category);
	//@Query("select count(*) from PRODUCT")
	
}
