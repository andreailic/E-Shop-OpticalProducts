package model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Customer")
public class Customer extends User {


	private int phone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Address")
	private Address address;

	public Customer() {
			
	}

	public Customer(User user) {
		super(user);
	}
	
	public Customer(User user, Address address, int phone) {
		super(user);
		this.address=address;
		this.phone=phone;
	}
	
	public Customer(User user, Set<Role> role, Address address, int phone) {
		super(user);
		super.setRole(role);
		this.address=address;
		this.phone=phone;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	

}