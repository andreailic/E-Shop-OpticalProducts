package model;

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
	private int id_brand;
	@Column(name="naziv_brenda")
	private String brand_name;
	
	@OneToMany(mappedBy="brand", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;
	
	

	public Brand() {
		
	}

	public Brand(int id_brand, String brand_name, List<Product> products) {
		super();
		this.id_brand = id_brand;
		this.brand_name = brand_name;
		this.products = products;
	}

	public int getId_brand() {
		return id_brand;
	}

	public void setId_brand(int id_brand) {
		this.id_brand = id_brand;
	}

	public String getBrandName() {
		return brand_name;
	}

	public void setBrandName(String brand_name) {
		this.brand_name = brand_name;
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
