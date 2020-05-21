package org.signal.bot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
@NoArgsConstructor
public class Subscriber {
    @Id
    private ObjectId id;
    private Long chatId;
    private String name;

    public Subscriber(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
}
