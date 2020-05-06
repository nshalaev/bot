package org.signal.bot.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PriceDTO {
    private String id;
    private BigDecimal ask;
    private BigDecimal bid;
}
