package root.model;

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
	private int role_id;
	
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	
	public Role() {
		
	}


	public Role( ERole name) {
		
		this.name = name;
	}


	public int getRole_id() {
		return role_id;
	}


	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	public ERole getName() {
		return name;
	}


	public void setName(ERole name) {
		this.name = name;
	}
	
	
	
	

}
