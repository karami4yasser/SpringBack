package com.example.demo.controller;
import java.net.URI;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Custumer;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustumerRepository;
import com.example.demo.repository.ProductRepository;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class CustumerController {
	
	@Autowired
	private CustumerRepository CustumerRepository; 
		
		
		

		@GetMapping("/custumer/{id}")
		ResponseEntity<?> getCustumer(@PathVariable String id){
		Optional<Custumer> Custumer = CustumerRepository.findById(id);
		 return Custumer.map(response -> ResponseEntity.ok().body(response))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		 
		}
		
		
		
		@PostMapping("/custumer")
		ResponseEntity<Custumer> createCustumer(@Validated @RequestBody Custumer custumer) throws URISyntaxException{
			
			Custumer result= CustumerRepository.save(custumer);
		  return ResponseEntity.created(new URI("/api/custumer" + result.getId())).body(result); 
		
		}
		
		
		@PutMapping("/custumer/{id}")
		ResponseEntity<Custumer> updateCustumer(@Validated @RequestBody Custumer custumer){
			Custumer result= CustumerRepository.save(custumer);
			return ResponseEntity.ok().body(result);
		}
		
		
		
		@DeleteMapping("/custumer/{id}")
		ResponseEntity<?> deleteCustumer(@PathVariable String id){
			CustumerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
}

