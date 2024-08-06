package org.atlas.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"org.atlas.notification",
	"org.atlas.framework"
})
public class NotificationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServerApplication.class, args);
	}

}
