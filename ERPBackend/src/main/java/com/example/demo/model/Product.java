package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Product")
public class Product {

	
	@Id
	@SequenceGenerator(name="PRODUCT_ID_GENERATOR", sequenceName="PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_ID_GENERATOR")
	private int productId;
	@Column(name="name")
	private String name;
	private String price;
	private String description;
	private String lager;
	
	
	// bi-directional many-to-one association to Brand
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="brandId")
		private Brand brand;
		
		
	//bi-directional many-to-one association to Category
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="categoryId")
		private Category category;
		
	
	public Product() {
		
	}
	
	
	public Product(int product_id, String product_name, String product_price, String description, String lager,
			Brand brand, Category category) {
		super();
		this.productId = product_id;
		this.name = product_name;
		this.price = product_price;
		this.description = description;
		this.lager = lager;
		this.brand = brand;
		this.category = category;
	}

	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLager() {
		return lager;
	}

	public void setLager(String lager) {
		this.lager = lager;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	
}
