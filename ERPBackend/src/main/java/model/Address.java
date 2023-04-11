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
@Table(name="Address")
public class Address {
	@Id
	@SequenceGenerator(name="ADDRESS_ID_GENERATOR", sequenceName="ADDRESS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDRESS_ID_GENERATOR")
	private int address_id;
	@Column(name="naziv_ulice")
	private String street;
	
	@Column(name="drzava")
	private String country;
	
	@Column(name="grad")
	private String city;
	
	@Column(name="postanski_broj")
	private int zip;
	
	@OneToMany(mappedBy="address", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	@JsonIgnore
	private List<Customer> customers ;
	
public Address() {
		
	}
	
	public Address(int address_id, String street, String country, String city, int zip, List<Customer> customers) {
		super();
		this.address_id = address_id;
		this.street = street;
		this.country = country;
		this.city = city;
		this.zip = zip;
		this.customers = customers;
	}



	public Address(Address adresa) {
		this.address_id=adresa.address_id;
		this.street=adresa.street;
		this.country=adresa.country;
		this.city=adresa.city;
		this.zip=adresa.zip;
	}

	
	
	public int getId_address() {
		return address_id;
	}

	public void setId_address(int id_address) {
		this.address_id = id_address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	
	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}



	public List<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setAddress(this);

		return customer;
	}
	

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setAddress(null);

		return customer;
	}

}