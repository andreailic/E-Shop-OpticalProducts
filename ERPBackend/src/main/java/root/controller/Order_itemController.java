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
import root.model.Order_item;
import root.repository.Order_itemRepository;

@CrossOrigin
@RestController
@RequestMapping("/order_item")
public class Order_itemController {

	  @Autowired
	  private Order_itemRepository order_itemRepository;
	  
	  @GetMapping("/order_item")
	    public Collection<Order_item> getAllOrder_item(){
			return order_itemRepository.findAll();}
	   
	    @GetMapping("/order_item/{id}")
	    public Order_item getOrder_item(@PathVariable("id") Integer id) {
		     Optional<Order_item> optionalOrder_item = order_itemRepository.findById(id);
		     if (optionalOrder_item.isPresent()) {
		         return optionalOrder_item.get();
		     } else {
		         throw new ResourceNotFoundException("Ne postoji stavka porudzbine sa id: " + id);
		     }
	    }   
	    @PostMapping("/order_item")
		public ResponseEntity<Order_item> createOrder_item(@RequestBody Order_item order_item) {
			if(!order_itemRepository.existsById(order_item.getOrder_item_id())) {
				order_itemRepository.save(order_item);
				return new ResponseEntity<Order_item>(HttpStatus.OK);
			}
			return new ResponseEntity<Order_item>(HttpStatus.CONFLICT);
		}
		
		 @PutMapping("/order_item/{id}")
			public ResponseEntity<Order_item> updateOrder_item(@PathVariable("id") int id, @RequestBody Order_item newOrder_item) {		
				Order_item order_item = order_itemRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Ne postoji stavka porudzbine sa id: " + id));
				order_item.setQuantity(newOrder_item.getQuantity());
				
				Order_item updateOrder_item = order_itemRepository.save(order_item);
				
				return ResponseEntity.ok(updateOrder_item);
			}
		
		 @DeleteMapping("/order_item/{id}")
			public ResponseEntity<Order_item> deleteOrder_item(@PathVariable("id") int id){
				if (!order_itemRepository.existsById(id)) {
					return new ResponseEntity<Order_item>(HttpStatus.NO_CONTENT);
				}
				
				order_itemRepository.deleteById(id);		
				return new ResponseEntity<Order_item>(HttpStatus.OK);
			}	
		
}
