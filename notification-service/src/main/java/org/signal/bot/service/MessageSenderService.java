package org.signal.bot.service;

import org.signal.bot.dto.PriceDTO;
import org.signal.bot.invoker.MongoServiceRESTInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    @Autowired
    private TelegramBotService botService;

    @Autowired
    private MongoServiceRESTInvoker mongoServiceRESTInvoker;

    public void sendMessage() {
        PriceDTO price = mongoServiceRESTInvoker.getLastPrice();
        String message = createMessage(price);
        botService.sendMessage(message);
    }

    private String createMessage(PriceDTO price) {
        return String.format("Best ask %s USD\nBest bid %s USD", price.getAsk(), price.getBid());
    }

}
