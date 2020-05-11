package org.signal.bot.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NotificationServiceRESTInvoker {

    @Value("${notification.service.host}")
    private String host;

    @Value("${notification.service.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public void pingNotificationService() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("notification").build();
        restTemplate.postForEntity(uriComponents.toUriString(), null, void.class);
    }
}
