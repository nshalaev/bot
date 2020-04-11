package org.signal.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.signal.bot.model.Subscriber;
import org.signal.bot.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private String botName;
    private String token;
    private String lastMessage;

    @Autowired
    private SubscriberRepository repository;

    public TelegramBotService(@Value("${bot.name}") String botName,
                              @Value("${bot.token}") String token) {
        this.botName = botName;
        this.token = token;
    }

    public synchronized void sendMessage(String message) {
        this.lastMessage = message;
        List<Subscriber> subscribers = repository.findAll();
        subscribers.forEach(subscriber ->
                executeMessage(createSendMessage(subscriber.getChatId(), message)));
    }

    private SendMessage createSendMessage(Long chatId, String text) {
        return new SendMessage()
                .enableMarkdown(true)
                .setChatId(chatId)
                .setText(text);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Exception while executing message", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (isSubscribe(update)) {
            handleSubscribe(update.getMessage().getChatId());
        } else if (isUnsubscribe(update)) {
            handleUnsubscribe(update.getMessage().getChatId());
        }
    }

    private boolean isSubscribe(Update update) {
        return update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().equals("/start");
    }

    private synchronized void handleSubscribe(Long chatId) {
        Subscriber subscriber = repository.findByChatId(chatId);
        if (subscriber == null) {
            saveSubscriber(chatId);
        }
        executeMessage(createSendMessage(chatId, lastMessage));
    }

    private void saveSubscriber(Long chatId) {
        Subscriber subscriber = new Subscriber();
        subscriber.setChatId(chatId);
        repository.save(subscriber);
    }

    private boolean isUnsubscribe(Update update) {
        return update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().equals("/stop");
    }

    private synchronized void handleUnsubscribe(Long chatId) {
        repository.deleteByChatId(chatId);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
