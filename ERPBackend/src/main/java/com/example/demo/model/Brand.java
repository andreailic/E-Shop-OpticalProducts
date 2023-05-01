package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Brand")
public class Brand {

	@Id
	@SequenceGenerator(name="BRAND_ID_GENERATOR", sequenceName="BRAND_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BRAND_ID_GENERATOR")
	private int brandId;
	@Column(name="brand_name")
	private String brandName;
	
	@OneToMany(mappedBy="brand", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;
	
	

	public Brand() {
		
	}

	public Brand(int id_brand, String brand_name, List<Product> products) {
		super();
		this.brandId = id_brand;
		this.brandName = brand_name;
		this.products = products;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brand_name) {
		this.brandName = brand_name;
	}
	
	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	
	
	public Product addProizvod(Product product) {
		getProduct().add(product);
		product.setBrand(this);

		return product;
	}

	public Product removeProizvod(Product product) {
		getProduct().remove(product);
		product.setBrand(null);

		return product;
	}
	
	
}
