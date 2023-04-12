package root.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>, PagingAndSortingRepository <Staff, Integer> {

	Collection<Staff> findStaffByNameIgnoreCase(String name);

	Staff findByEmail(String email);
}
