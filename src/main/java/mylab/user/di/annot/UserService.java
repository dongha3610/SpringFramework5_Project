package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component	// 빈 이름 = "userService"
public class UserService {
	
	@Autowired	// UserRepository 타입의 빈을 자동 주입
    private UserRepository userRepository;
        
	@Autowired	// SecurityService 타입의 빈을 자동 주입
    private SecurityService securityService;
        
    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }
    
    public boolean registerUser(String userId, String name, String password) {
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }
        return false;
    }
}
