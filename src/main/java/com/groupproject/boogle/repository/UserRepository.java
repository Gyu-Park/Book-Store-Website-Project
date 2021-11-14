package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.User;

/** The repository is where the data is stored.
 * We can manipulate (retrieve, delete, update, etc.) data with repository **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	
	User findByUserid(Long userid);
	
}
