package org.signal.bot.controller;

import org.signal.bot.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationServiceController {

    @Autowired
    private TelegramBotService botService;

    @PostMapping("/notification")
    public void sendNotification(@RequestBody String notification) {
        botService.sendMessage(notification);
    }

}
