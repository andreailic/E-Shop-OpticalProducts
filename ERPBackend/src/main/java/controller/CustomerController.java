package controller;


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Customer;
import repository.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
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
    
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer newCustomer) {		
    	Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji kupac sa id: " + id));
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
