package org.signal.bot.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Subscriber {
    @Id
    private ObjectId id;
    private Long chatId;
    private String name;
}
