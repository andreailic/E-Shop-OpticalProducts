package model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="User")
public class User {

	@Id
	@SequenceGenerator(name="USER_ID_GENERATOR", sequenceName="USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_GENERATOR")
	private int user_id;
	private String name;
	private String surname;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String user_name;
	@NotBlank
	private String password;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	
	
	private Set<Role> role= new HashSet<>();
	
	
	
	public User() {
		
	}


	public User(int user_id, String name, String surname, @NotBlank @Email String email, @NotBlank String user_name,
			@NotBlank String password, Set<Role> role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.user_name = user_name;
		this.password = password;
		this.role = role;
	}

	public User( @NotBlank @Email String email, @NotBlank String user_name,
			@NotBlank String password) {
		
		this.email = email;
		this.user_name = user_name;
		this.password = password;
		
	}

	public User(User user) {
		this.name=user.name;
		this.surname = user.surname;
		this.email = user.email;
		this.user_name = user.user_name;
		this.password = user.password;
		this.role = user.role;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public Set<Role> getRole() {
		return role;
	}


	public void setRole(Set<Role> role) {
		System.out.println("Role:" + role.size());
		this.role = role;
	}
	
	
}
