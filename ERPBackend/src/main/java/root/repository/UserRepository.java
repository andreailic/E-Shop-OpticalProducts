package root.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository  extends JpaRepository<User, Integer>, PagingAndSortingRepository <User, Integer> {

	Optional<User> findUserByEmail(String email);
	Boolean existsByEmail(String email);
}
