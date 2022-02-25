package com.example.demo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
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
import com.example.demo.model.Product;
import com.example.demo.model.orderitems;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CustumerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderitemsRepository;
@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class OrderitemsController {
	
	@Autowired
	private OrderRepository OrderRepository; 
	@Autowired
	private OrderitemsRepository OrderitemsRepository; 
	@Autowired
	private ProductRepository ProductRepository; 
	
	@GetMapping("/orderitems")
	List<orderitems> getOrderitems(){
		return OrderitemsRepository.findAll();
	}
	
	@PostMapping("/orderitem")
	ResponseEntity<orderitems> createCustumer(@Validated @RequestBody orderitems orderitems) throws URISyntaxException{
		
		orderitems result= OrderitemsRepository.save(orderitems);
	  return ResponseEntity.created(new URI("/api/orderitem" + result.getId())).body(result); 
	
	}
	
	@PutMapping("/order/{orderId}/orderitem/{orderitemId}/product/{productId}/{quantity}")
	orderitems assignorderTocustumer(
            @PathVariable Long orderitemId,
            @PathVariable Long productId,
            @PathVariable Long quantity,
            @PathVariable Long orderId
    ) {
		orderitems Orderitem = OrderitemsRepository.findById(orderitemId).get();
		Order Order = OrderRepository.findById(orderId).get();
        Product Product = ProductRepository.findById(productId).get();
        if(Product.getStock()>1) {
        	
        	 Orderitem.setOrder(Order);
             Product.setStock(Product.getStock()-1);
             Orderitem.setProduct(Product);
             Orderitem.setQuantity(quantity);
             orderitems result= OrderitemsRepository.save(Orderitem);
             return result; 
        }
        else {
        	orderitems result= new orderitems() ;
        	return result;
        }
        
       
    }
	
	
	
	
	
	

}
