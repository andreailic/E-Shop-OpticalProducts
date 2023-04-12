package root.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import root.exception.ResourceNotFoundException;
import root.model.Orders;
import root.repository.OrdersRepository;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private OrdersRepository ordersRepository;
    
    @GetMapping("/orders")
    public Collection<Orders> getAllOrders(){
		return ordersRepository.findAll();}
    
    @GetMapping("/orders/{id}")
    public Orders getOrders(@PathVariable("id") Integer id) {
	     Optional<Orders> optionalOrders = ordersRepository.findById(id);
	     if (optionalOrders.isPresent()) {
	         return optionalOrders.get();
	     } else {
	         throw new ResourceNotFoundException("Ne postoji porudzbina sa id: " + id);
	     }
	 }
    @PostMapping("/orders")
	public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
		if(!ordersRepository.existsById(orders.getOrder_id())) {
			ordersRepository.save(orders);
			return new ResponseEntity<Orders>(HttpStatus.OK);
		}
		return new ResponseEntity<Orders>(HttpStatus.CONFLICT);
	}
    
    @PutMapping("/orders/{id}")
   	public ResponseEntity<Orders> updateOrders(@PathVariable("id") int id, @RequestBody Orders newOrders) {		
    	Orders orders = ordersRepository.findById(id)
   				.orElseThrow(() -> new ResourceNotFoundException("Ne postoji porudzbina sa id: " + id));
   		orders.setOrder_date(newOrders.getOrder_date());
   		orders.setOrder_status(newOrders.getOrder_status());
   		orders.setOrder_price(newOrders.getOrder_price());
   		
   		Orders updatedOrders = ordersRepository.save(orders);
   		
   		return ResponseEntity.ok(updatedOrders);
   	}
    
    @DeleteMapping("/orders/{id}")
   	public ResponseEntity<Orders> deleteOrders(@PathVariable("id") int id){
   		if (!ordersRepository.existsById(id)) {
   			return new ResponseEntity<Orders>(HttpStatus.NO_CONTENT);
   		}
   		
   		ordersRepository.deleteById(id);		
   		return new ResponseEntity<Orders>(HttpStatus.OK);
   	}
}
