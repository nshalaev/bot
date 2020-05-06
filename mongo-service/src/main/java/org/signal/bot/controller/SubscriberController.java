package org.signal.bot.controller;

import org.signal.bot.dto.SubscriberDTO;
import org.signal.bot.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/subscribers")
    public List<SubscriberDTO> getAll() {
        return subscriberService.getAll();
    }

    @PutMapping("/subscribers")
    public void create(@RequestBody SubscriberDTO subscriberDTO) {
        subscriberService.create(subscriberDTO);
    }

    @DeleteMapping("/subscribers/{chat_id}")
    public void deleteByChatId(@PathVariable(name = "chat_id") Long chatId) {
        subscriberService.deleteByChatId(chatId);
    }



}
