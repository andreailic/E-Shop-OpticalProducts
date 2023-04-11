package model;

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
	
	private int category_id;

	@Column(name="naziv_kategorija")
	private String category_name;

	@OneToMany(mappedBy="category", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	@JsonIgnore
	private List<Product> products;

	public Category() {
	}


	
	public Category(int category_id, String category_name, List<Product> products) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.products = products;
	}


	public Category (Category category) {
		this.category_id = category.category_id;
		this.category_name = category.category_name;
		
	}

	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
	


