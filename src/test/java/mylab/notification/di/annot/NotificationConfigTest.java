package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)	// XML 대신 설정 클래스
public class NotificationConfigTest {
	@Autowired
	NotificationManager notificationManager;
	
	@Test
	void notificationManager() {
		// a. NotificationManager 주입 확인
		assertNotNull(notificationManager);
		
		// b. 이메일 서비스 검증
		assertNotNull(notificationManager.getEmailService());
		// getEmailService() 의 리턴 타입은 NotificationService(인터페이스)라서
		// getSmtpServer() 를 쓰려면 구현 클래스로 형변환(casting) 해야 한다
		EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
		assertEquals("smtp.gmail.com", emailService.getSmtpServer());
		assertEquals(587, emailService.getPort());
		
		// c. SMS 서비스 검증
		assertNotNull(notificationManager.getSmsService());
		SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
		assertEquals("SKT", smsService.getProvider());
		
		// d. NotificationManager 의 메서드 실행
		notificationManager.sendNotificationByEmail("테스트 이메일");
		notificationManager.sendNotificationBySms("테스트 SMS");
	}
}
