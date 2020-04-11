package org.signal.bot.service;

import org.signal.bot.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    @Autowired
    private TelegramBotService bot;

    @Autowired
    private PriceService service;

    @Scheduled(fixedDelay = 1800000)
    public void sendMessage() {
        Price price = service.getPrice();
        String message = String.format("Best ask %s USD\nBest bid %s USD", price.getAsk(), price.getBid());
        bot.sendMessage(message);
    }

}
