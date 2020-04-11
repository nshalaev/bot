package org.signal.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Price {
    private BigDecimal ask;
    private BigDecimal bid;
}
