package org.signal.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceRESTInvoker {

    @Autowired
    private RestTemplate restTemplate;

    public void pingNotificationService() {
        restTemplate.getForEntity("http://localhost:8081/sendNotification", void.class);
    }
}
