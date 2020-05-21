package org.signal.bot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter @Setter
@NoArgsConstructor
public class Price {
    @Id
    private ObjectId id;
    private BigDecimal ask;
    private BigDecimal bid;

    public Price(BigDecimal ask, BigDecimal bid) {
        this.ask = ask;
        this.bid = bid;
    }
}

