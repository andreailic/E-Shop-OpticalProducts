package com.example.demo.model;

import java.io.Serializable;
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

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORY_ID_GENERATOR", sequenceName="CATEGORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_ID_GENERATOR")
	private int categoryId;

	@Column(name="category_name")
	private String categoryName;

	@OneToMany(mappedBy="category", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	@JsonIgnore
	private List<Product> products;

	public Category() {
	}


	
	public Category(int categoryId, String category_name, List<Product> products) {
		super();
		this.categoryId = categoryId;
		this.categoryName = category_name;
		this.products = products;
	}


	public Category (Category category) {
		this.categoryId = category.categoryId;
		this.categoryName = category.categoryName;
		
	}

	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}
	


