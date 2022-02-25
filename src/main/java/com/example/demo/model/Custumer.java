package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="CUSTUMERR")
public class Custumer {

	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String fullname;
	
	private String email;
	
	private String adresse;
	
	private String type;
	
	
    @OneToMany(mappedBy = "custumer")
	
	private List<Order> order;
	
	
}
