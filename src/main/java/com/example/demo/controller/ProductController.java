package com.example.demo.controller;
import com.example.demo.model.Category;
import  com.example.demo.model.Product;

import java.net.URI;
import java.net.URISyntaxException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class ProductController {
	
	
	@Autowired
	private ProductRepository ProductRepository; 
  
	
	@GetMapping("/products")
	ResponseEntity<Map<String, Object>> getProducts(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
		Pageable paging = PageRequest.of(page, size);
		
		List<Product> Product = ProductRepository.findAllByOrderByPriceDesc(paging);

		Long count =   ProductRepository.count();
	    Map<String, Object> response = new HashMap<>();
	    response.put("products",Product);
	    response.put("count",count);
	    

		 return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/products/category/{category}/non")
	List<Product> getproductsByCategory(@PathVariable String category,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
		Pageable paging = PageRequest.of(page, size);
	List<Product> Product = ProductRepository.findByCategory(category,paging);
	 return Product;}
	
	@GetMapping("/products/category/{category}")
	ResponseEntity<Map<String, Object>> getCountproductsByCategory(@PathVariable String category,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
		Pageable paging = PageRequest.of(page, size);
	List<Product> Product = ProductRepository.findByCategory(category,paging);
	
	
	Long count =   ProductRepository.countBycategory(category);
    Map<String, Object> response = new HashMap<>();
    response.put("products",Product);
    response.put("count",count);
    

	 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	
	 @GetMapping("/products/name/{name}")
		List<Product> getproductsByTitle(@PathVariable String name){
		List<Product> Product = ProductRepository.findByTitle(name);
		 return Product;
	 
	}
	@GetMapping("/product/{id}")
	ResponseEntity<?> getProduct(@PathVariable Long id){
	Optional<Product> Product = ProductRepository.findById(id);
	 return Product.map(response -> ResponseEntity.ok().body(response))
			 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 
	}

	@DeleteMapping("/product/{id}")
	ResponseEntity<?>  deleteProduct(@PathVariable Long id){
		ProductRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/product")
	ResponseEntity<Product> createProduct(@Validated @RequestBody Product product) throws URISyntaxException{
		Product result= ProductRepository.save(product);
		return ResponseEntity.created(new URI("/api/products" + result.getId())).body(result);
	}
	@PutMapping("/product")
	ResponseEntity<Product> updateProduct(@Validated @RequestBody Product product) throws URISyntaxException{
		Product result= ProductRepository.save(product);
		return ResponseEntity.created(new URI("/api/products" + result.getId())).body(result);
	}
}
