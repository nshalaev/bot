package org.signal.bot.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter @Setter
public class Price {
    @Id
    private ObjectId id;
    private BigDecimal ask;
    private BigDecimal bid;
}
