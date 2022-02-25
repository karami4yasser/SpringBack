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
import com.example.demo.model.Order;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustumerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.OrderRepository;
@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class OrderController {
	private OrderRepository OrderRepository; 
	private CustumerRepository CustumerRepository; 

	public OrderController(
			CustumerRepository CustumerRepository,
			OrderRepository OrderRepository) {
		super();
		this.OrderRepository = OrderRepository;
		this.CustumerRepository = CustumerRepository;
	}
	
	
	
	
	
	
	@GetMapping("/order/{id}")
	ResponseEntity<?> getOrder(@PathVariable Long id){
	Optional<Order> Order = OrderRepository.findById(id);
	 return Order.map(response -> ResponseEntity.ok().body(response))
			 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 
	}
	
	
	
	@PostMapping("/order")
	ResponseEntity<Order> createOrder(@Validated @RequestBody Order order) throws URISyntaxException{
		
		Order result= OrderRepository.save(order);
	  return ResponseEntity.created(new URI("/api/order" + result.getId())).body(result); 
	
	}
	
	@PutMapping("/order/{orderId}/custumer/{custumerId}")
	Order assignorderTocustumer(
            @PathVariable Long orderId,
            @PathVariable String custumerId
    ) {
		Order Order = OrderRepository.findById(orderId).get();
        Custumer Custumer = CustumerRepository.findById(custumerId).get();
        Order.setCustumer(Custumer);
        Order result= OrderRepository.save(Order);
        return result; 
    }
	
	
	
	
	
	

}
