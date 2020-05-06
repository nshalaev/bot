package org.signal.bot.invoker;

import org.signal.bot.dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MongoServiceRESTInvoker {

    @Autowired
    private RestTemplate restTemplate;

    public void save(PriceDTO price) {
        restTemplate.put("http://localhost:8088/prices", price);
    }

}
