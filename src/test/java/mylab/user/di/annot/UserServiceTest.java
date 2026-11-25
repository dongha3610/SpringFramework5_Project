package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Test
	void userService() {
		// UserService 주입 확인
		assertNotNull(userService);
		
		// UserRepository 주입 확인 + @Value 값 확인
		assertNotNull(userService.getUserRepository());
		assertEquals("MySQL", userService.getUserRepository().getDbType());
		
		// SecurityService 주입 확인
		assertNotNull(userService.getSecurityService());
	}
	
	@Test
	void registerUser() {
		// password 가 전달되면 true
		assertTrue(userService.registerUser("user1", "홍길동", "1234"));
		
		// password 가 전달되지 않으면 false
		assertFalse(userService.registerUser("user2", "김철수", null));
		assertFalse(userService.registerUser("user3", "이영희", ""));
	}
}
