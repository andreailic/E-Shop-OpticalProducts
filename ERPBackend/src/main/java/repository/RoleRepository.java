package repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.ERole;
import model.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>,PagingAndSortingRepository <Role, Long>{

	Optional<Role> findByName(ERole name);
}
