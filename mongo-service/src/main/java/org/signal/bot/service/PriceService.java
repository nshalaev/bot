package org.signal.bot.service;

import org.signal.bot.dto.PriceDTO;
import org.signal.bot.model.Price;
import org.signal.bot.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceDTO getLastPrice() {
        Price price = priceRepository.findFirstByOrderByIdDesc();
        return createDTOFRomEntity(price);
    }

    private PriceDTO createDTOFRomEntity(Price price) {
        return PriceDTO.builder()
                .id(price.getId().toString())
                .ask(price.getAsk())
                .bid(price.getBid())
                .build();
    }

    public void save(PriceDTO dto) {
        Price price = new Price();
        price.setAsk(dto.getAsk());
        price.setBid(dto.getBid());
        priceRepository.save(price);
    }
}
