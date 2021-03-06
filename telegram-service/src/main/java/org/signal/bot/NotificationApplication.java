package org.signal.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class NotificationApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(NotificationApplication.class, args);
    }

}
