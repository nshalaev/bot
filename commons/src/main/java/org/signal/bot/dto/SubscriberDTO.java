package org.signal.bot.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SubscriberDTO {
    private String id;
    private Long chatId;
    private String name;
}
