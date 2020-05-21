package org.signal.bot.service;

import org.signal.bot.domain.Subscriber;
import org.signal.bot.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    public void addSubscriber(Long chatId, String username) {
        if (!ofNullable(subscriberRepository.findByChatId(chatId)).isPresent()) {
            subscriberRepository.save(new Subscriber(chatId, username));
        }
    }

    public void deleteByChatId(Long chatId) {
        subscriberRepository.deleteByChatId(chatId);
    }
}
