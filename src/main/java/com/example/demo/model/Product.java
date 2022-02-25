package com.example.demo.model;

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
@Table(name="PRODUCTT")
public class Product {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long rating;

	private String title;
	private String image ;
	private String category;
	private Float price;
	private String description;
	private Long Stock;
	
	//@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "category_id", referencedColumnName = "id")
    //private Category cate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fournisseur_id", referencedColumnName = "id")
    private Founisseur fournisseur;
	
@JsonIgnore
@OneToMany(mappedBy = "product")
	
	private List<orderitems> orderitems;
	
	
}
