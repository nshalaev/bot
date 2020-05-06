package org.signal.bot.invoker;

import org.signal.bot.dto.PriceDTO;
import org.signal.bot.dto.SubscriberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoServiceRESTInvoker {

    @Autowired
    private RestTemplate restTemplate;

    public List<SubscriberDTO> findAllSubscribers() {
        SubscriberDTO[] subscriberDTOList = restTemplate
                .getForEntity("http://localhost:8088/subscribers", SubscriberDTO[].class).getBody();
        return Arrays.asList(subscriberDTOList);
    }

    public void createSubscriber(Long chatId, String username) {
        restTemplate.put(
                "http://localhost:8088/subscribers/",
                SubscriberDTO.builder().chatId(chatId).name(username).build());
    }

    public void deleteByChatId(Long chatId) {
        restTemplate.delete("http://localhost:8088/subscribers/" + chatId);
    }

    public PriceDTO getLastPrice() {
        ResponseEntity<PriceDTO> entity = restTemplate.getForEntity(
                "http://localhost:8088/prices/last",
                PriceDTO.class);
        return entity.getBody();
    }
}
