package org.signal.bot.service;

import org.signal.bot.dto.SubscriberDTO;
import org.signal.bot.model.Subscriber;
import org.signal.bot.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<SubscriberDTO> getAll() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        return subscribers.stream()
                .map(e -> createDTOFRomEntity(e))
                .collect(Collectors.toList());
    }

    private SubscriberDTO createDTOFRomEntity(Subscriber subscriber) {
        return SubscriberDTO.builder()
                .id(subscriber.getId().toString())
                .chatId(subscriber.getChatId())
                .name(subscriber.getName())
                .build();
    }

    public void create(SubscriberDTO subscriberDTO) {
        Long chatId = subscriberDTO.getChatId();
        Subscriber subscriber = subscriberRepository.findByChatId(chatId);
        if (subscriber == null) {
            subscriber = new Subscriber();
            subscriber.setChatId(chatId);
            subscriber.setName(subscriberDTO.getName());
            subscriberRepository.save(subscriber);
        }
    }

    public void deleteByChatId(Long chatId) {
        subscriberRepository.deleteByChatId(chatId);
    }
}
