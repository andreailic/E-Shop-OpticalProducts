package repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserRepository  extends JpaRepository<User, Integer>, PagingAndSortingRepository <User, Integer> {

	Optional<User> findUserByEmail(String email);
	Boolean existsByEmail(String email);
}
