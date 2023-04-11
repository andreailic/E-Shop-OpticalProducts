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
import model.Staff;
import repository.StaffRepository;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {

	 @Autowired
	    private StaffRepository staffRepository;
	 
	 @GetMapping("/staff") 
	    public Collection<Staff> getAllStaff(){
	    	return staffRepository.findAll();
	    }
	 @GetMapping("/staff/{id}")
	    public Staff getStaff(@PathVariable("id") Integer id) {
		     Optional<Staff> optionalStaff = staffRepository.findById(id);
		     if (optionalStaff.isPresent()) {
		         return optionalStaff.get();
		     } else {
		         throw new ResourceNotFoundException("Ne postoji radnik sa id: " + id);
		     }
		 }
	 @PutMapping("/staff/{id}")
	    public ResponseEntity<Staff> updateStaff(@PathVariable("id") int id, @RequestBody Staff newStaff) {		
		 Staff staff = staffRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Ne postoji radnik sa id: " + id));
		 staff.setPosition(newStaff.getPosition());
	        
		 Staff updatedStaff = staffRepository.save(staff);
	        return ResponseEntity.ok(updatedStaff);
	    }
	 @DeleteMapping("/staff/{id}")
	   	public ResponseEntity<Staff> deleteStaff(@PathVariable("id") int id){
	   		if (!staffRepository.existsById(id)) {
	   			return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
	   		}
	   		
	   		staffRepository.deleteById(id);		
	   		return new ResponseEntity<Staff>(HttpStatus.OK);
	   	}
}
