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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.User;
import repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
    private UserRepository userRepository;
	
    @GetMapping("/user")
	    public Collection<User> getAllUser(){
			return userRepository.findAll();}
	 
	 
	 @GetMapping("/user/{id}")
	 public User getUser(@PathVariable("id") Integer id) {
	     Optional<User> optionalUser = userRepository.findById(id);
	     if (optionalUser.isPresent()) {
	         return optionalUser.get();
	     } else {
	         throw new ResourceNotFoundException("Ne postoji korisnik sa id: " + id);
	     }
	 }

	 @PostMapping("/user")
		public ResponseEntity<User> postUser(@RequestBody User user){
			if (!userRepository.existsById(user.getUser_id())) {
				userRepository.save(user);
				return new ResponseEntity<User>(HttpStatus.OK);
			}
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		
		@PutMapping("/user")
		public ResponseEntity<User> putUser(@RequestBody User user) {
			if (!userRepository.existsById(user.getUser_id())){
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			}
			userRepository.save(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		
		@DeleteMapping("/user/{id}")
		public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
			if (!userRepository.existsById(id))
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			userRepository.deleteById(id);
			
			return new ResponseEntity<User>(HttpStatus.OK);
		}

}