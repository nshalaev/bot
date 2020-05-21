package org.signal.bot.controller;

import org.signal.bot.dto.SubscriberDTO;
import org.signal.bot.invoker.TelegramServiceRESTInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriberController {

    @Autowired
    private TelegramServiceRESTInvoker invoker;

    @GetMapping
    public List<SubscriberDTO> getSubscribers() {
        return invoker.findAllSubscribers();
    }

}
