package com.example.demo.controller;


import java.util.Collection;
import java.util.Optional;


import com.example.demo.model.ERole;
import com.example.demo.model.User;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;
	private final PasswordEncoder passwordEncoder;

	public CustomerController(CustomerRepository customerRepository,
							  AddressRepository addressRepository,
							  PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/customer")
    public Collection<Customer> getAllCustomer(){
    	return customerRepository.findAll();
    }
    
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
	     Optional<Customer> optionalCustomer = customerRepository.findById(id);
	     if (optionalCustomer.isPresent()) {
	         return optionalCustomer.get();
	     } else {
	         throw new ResourceNotFoundException("Ne postoji kupac sa id: " + id);
	     }
	 }

	@PostMapping("/customer")
	public ResponseEntity<User> postUser(@RequestBody Customer customer){
		var addressOpt = addressRepository.findById(customer.getAddress().getAddressId());
		if (addressOpt.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

		customer.setAddress(addressOpt.get());
		customer.getUser().setPassword(passwordEncoder.encode(customer.getUser().getPassword()));
		customer.getUser().setRole(ERole.ROLE_CUSTOMER);
		if (!customerRepository.existsById(customer.getId())) {
			customerRepository.save(customer);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);
	}
    
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer newCustomer) {		
    	Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji kupac sa id: " + id));

		customer.getUser().setName(newCustomer.getUser().getName());
		customer.getUser().setSurname(newCustomer.getUser().getSurname());
        customer.setPhone(newCustomer.getPhone());
        customer.setAddress(newCustomer.getAddress());
        
        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    @DeleteMapping("/customer/{id}")
   	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id){
   		if (!customerRepository.existsById(id)) {
   			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
   		}
   		
   		customerRepository.deleteById(id);		
   		return new ResponseEntity<Customer>(HttpStatus.OK);
   	}
   	
}
