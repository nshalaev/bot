package org.signal.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.signal.bot.dto.PriceDTO;
import org.signal.bot.invoker.MongoServiceRESTInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class PriceService {

    private final String BTC_USD_PRICE_API = "https://api.livecoin.net/exchange/ticker?currencyPair=BTC/USD";

    @Autowired
    private MongoServiceRESTInvoker invoker;

    @Autowired
    private RestTemplate restTemplate;

    public void handleNewPrice() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BTC_USD_PRICE_API, String.class);
        JSONObject json = new JSONObject(responseEntity.getBody());
        PriceDTO price = parsePriceFromJSON(json);
        invoker.save(price);
    }

    private PriceDTO parsePriceFromJSON(JSONObject json) {
        BigDecimal bestBid =  json.getBigDecimal("best_bid").setScale(4, RoundingMode.DOWN);
        BigDecimal bestAsk = json.getBigDecimal("best_ask").setScale(4, RoundingMode.DOWN);
        log.info("Best bid {} USD. Best ask {} USD.", bestBid, bestAsk);
        return PriceDTO.builder()
                .ask(bestAsk)
                .bid(bestBid)
                .build();
    }
}
