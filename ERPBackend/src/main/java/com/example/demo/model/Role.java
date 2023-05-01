package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Role {
	@Id
	@SequenceGenerator(name="ROLE_ID_GENERATOR", sequenceName="ROLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ID_GENERATOR")
	private int roleId;
	
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	
	public Role() {
		
	}


	public Role( ERole name) {
		
		this.name = name;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public ERole getName() {
		return name;
	}


	public void setName(ERole name) {
		this.name = name;
	}
	
	
	
	

}
