package org.signal.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.signal.bot.model.Price;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
@Service
public class PriceService {

    private final String BTC_USD_PRICE_API = "https://api.livecoin.net/exchange/ticker?currencyPair=BTC/USD";

    public Price getPrice() {
        try {
            URLConnection connection = createConnection();
            JSONObject json = readJSONFromConnection(connection);
            BigDecimal bestBid =  json.getBigDecimal("best_bid");
            BigDecimal bestAsk = json.getBigDecimal("best_ask");
            log.info("Best bid {} USD. Best ask {} USD.", bestBid, bestAsk);
            return new Price(bestAsk, bestBid);
        } catch (Exception e) {
            log.error("Exception while getting price from exchange", e);
            throw new RuntimeException(e);
        }
    }

    private URLConnection createConnection() throws IOException {
        URL queryUrl = new URL(BTC_USD_PRICE_API);
        URLConnection connection = queryUrl.openConnection();
        connection.setDoOutput(true);
        return connection;
    }

    private JSONObject readJSONFromConnection(URLConnection connection) throws IOException {
        try(InputStream inputStream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String strJSON = sb.toString();
            return new JSONObject(strJSON);
        }
    }
}
