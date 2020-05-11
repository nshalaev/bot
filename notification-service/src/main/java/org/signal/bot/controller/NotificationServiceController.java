package org.signal.bot.controller;

import org.signal.bot.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationServiceController {

    @Autowired
    private MessageSenderService senderService;

    @PostMapping("/notification")
    public void sendNotification() {
        senderService.sendMessage();
    }

}
