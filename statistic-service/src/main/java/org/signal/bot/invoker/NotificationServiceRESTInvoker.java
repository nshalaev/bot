package org.signal.bot.invoker;

import org.signal.bot.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NotificationServiceRESTInvoker {

    @Value("${telegram.service.host}")
    private String host;

    @Value("${telegram.service.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public void pingNotificationService(Price price) {
        String notification = String
                .format("Best ask %s USD\nBest bid %s USD", price.getAsk(), price.getBid());
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("notification").build();
        restTemplate.postForEntity(uriComponents.toUriString(), notification, void.class);
    }
}
