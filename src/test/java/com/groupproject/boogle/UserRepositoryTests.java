package com.groupproject.boogle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	/**
	@Test
	public void testCreateUser( ) {
		User user = new User();
		user.setEmail("kyesung8282@gmail.com");
		user.setPassword("0000");
		user.setFirstname("Gyuseok");
		user.setLastname("Park");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getUserid());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "kyesung8282@gmail.com";
		
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	} **/
}
