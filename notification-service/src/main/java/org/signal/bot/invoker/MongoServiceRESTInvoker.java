package org.signal.bot.invoker;

import org.signal.bot.dto.PriceDTO;
import org.signal.bot.dto.SubscriberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoServiceRESTInvoker {

    @Value("${mongo.service.host}")
    private String host;

    @Value("${mongo.service.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public List<SubscriberDTO> findAllSubscribers() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("subscribers").build();
        SubscriberDTO[] subscriberDTOList = restTemplate
                .getForEntity(uriComponents.toUriString(),
                        SubscriberDTO[].class).getBody();
        return Arrays.asList(subscriberDTOList);
    }

    public void createSubscriber(Long chatId, String username) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("subscribers").build();
        restTemplate.put(
                uriComponents.toUriString(),
                SubscriberDTO.builder().chatId(chatId).name(username).build());
    }

    public void deleteByChatId(Long chatId) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("subscribers")
                .fragment(chatId.toString()).build();
        restTemplate.delete(uriComponents.toUriString());
    }

    public PriceDTO getLastPrice() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port)
                .pathSegment("prices", "last").build();
        ResponseEntity<PriceDTO> entity = restTemplate.getForEntity(
                uriComponents.toUriString(),
                PriceDTO.class);
        return entity.getBody();
    }
}
