package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Builder
public class Estimation {

    @With Integer id;
    @With Card card;
    Integer cardId;
    @With Integer taskId;
    Integer guestUserId;
    LocalDateTime createdAt;
    @With Boolean active;
}
