package hh.sof03.bookstore.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void createUser() {
		User user = new User("visitor", "$2a$10$cllmhpCiAFbg2.5ddACS0e2Nozf7QOw9wdruxSuha0DZpY/1t8Cjm", "visitor@mail.com", "user");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		userRepository.deleteById(1L);
		boolean userExistsAfterDeletion = userRepository.existsById(1L);
		assertThat(userExistsAfterDeletion).isFalse();
	}
	
	@Test
	public void findByEmailShouldReturnUser() {
		List<User> users = userRepository.findByEmail("user@email.com");
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("user");
	}

}
