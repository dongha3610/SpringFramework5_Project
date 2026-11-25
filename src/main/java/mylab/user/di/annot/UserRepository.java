package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component	// 빈 이름 = "userRepository"
public class UserRepository {
   @Value("MySQL")	// 문자열 값 "MySQL"을 바로 주입
   private String dbType;
    
    public UserRepository() {}
    
    public String getDbType() { return dbType; }
    public void setDbType(String dbType) { this.dbType = dbType; }
    
    public boolean saveUser(String userId, String name) {
        System.out.println("사용자 저장: " + userId + ", " + name + " (DB: " + dbType + ")");
        return true;
    }
    
    @Override
    public String toString() {
        return "UserRepository [dbType=" + dbType + "]";
    }
}
