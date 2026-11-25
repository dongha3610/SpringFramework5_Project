package mylab.notification.di.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 이 클래스가 설정 파일 (= XML 의 <beans>)
public class NotificationConfig {
	

	@Bean
	public EmailNotificationService emailNotificationService() {
		return new EmailNotificationService("smtp.gmail.com", 587);
	}
	
	
	@Bean
	public SmsNotificationService smsNotificationService() {
		return new SmsNotificationService("SKT");
	}
	
	
	@Bean
	public NotificationManager notificationManager() {
		
		return new NotificationManager(emailNotificationService(), smsNotificationService());
	}
}
