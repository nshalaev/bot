package org.signal.bot.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Subscriber {
    @Id
    private ObjectId id;
    private Long chatId;
    private String name;
}
