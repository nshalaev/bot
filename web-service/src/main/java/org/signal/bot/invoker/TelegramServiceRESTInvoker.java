package org.signal.bot.invoker;

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
public class TelegramServiceRESTInvoker {

    @Value("${telegram.service.host}")
    private String host;

    @Value("${telegram.service.port}")
    private Integer port;

    @Autowired
    private RestTemplate restTemplate;

    public List<SubscriberDTO> findAllSubscribers() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("subscribers").build();
        ResponseEntity<SubscriberDTO[]> subscriberDTOList = restTemplate
                .getForEntity(uriComponents.toUriString(), SubscriberDTO[].class);
        return Arrays.asList(subscriberDTOList.getBody());
    }

}
