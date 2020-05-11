package org.signal.bot.invoker;

import org.signal.bot.dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MongoServiceRESTInvoker {

    @Value("${mongo.service.host}")
    private String host;

    @Value("${mongo.service.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public void save(PriceDTO price) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host).port(port).path("prices").build();
        restTemplate.put(uriComponents.toUriString(), price);
    }

}
