package org.signal.bot.controller;

import org.signal.bot.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationServiceController {

    @Autowired
    private MessageSenderService senderService;

    @GetMapping("/sendNotification")
    public void sendNotification() {
        senderService.sendMessage();
    }

}
