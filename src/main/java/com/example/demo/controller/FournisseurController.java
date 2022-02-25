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
import com.example.demo.model.Founisseur;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustumerRepository;
import com.example.demo.repository.FournisseurRepository;
import com.example.demo.repository.ProductRepository;


@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class FournisseurController {
	@Autowired
	private FournisseurRepository FournisseurRepository; 
		
		
		

		@GetMapping("/fournisseur/{id}")
		ResponseEntity<?> getFournisseur(@PathVariable Long id){
		Optional<Founisseur> Founisseur = FournisseurRepository.findById(id);
		 return Founisseur.map(response -> ResponseEntity.ok().body(response))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		 
		}

}
