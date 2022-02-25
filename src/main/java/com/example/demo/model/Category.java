package com.example.demo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="categoryY")
public class Category  {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	
	 //@OneToMany(mappedBy = "category")
	
	//private List<Product> products;
    
	private String name;
	
	

}
