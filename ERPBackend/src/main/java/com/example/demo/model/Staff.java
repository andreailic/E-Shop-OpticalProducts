package com.example.demo.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="Staff")
public class Staff extends User{
	
	private String position;
	
	public Staff() {
		
	}
	
	public Staff(String position) {
		super();
		this.position = position;
	}

	public Staff(User user, String position) {
		super(user);
		this.position = position;
	}

	public Staff(User user, Set<Role> role, String position) {
		super(user);
		super.setRole(role);
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
