package org.signal.bot.controller;

import org.signal.bot.dto.PriceDTO;
import org.signal.bot.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices/last")
    public PriceDTO getLastPrice() {
        return priceService.getLastPrice();
    }

    @PutMapping("/prices")
    public void save(@RequestBody PriceDTO price) {
        priceService.save(price);
    }

}
