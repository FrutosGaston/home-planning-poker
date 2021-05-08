package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Builder
public class Estimation {
    @With
    Integer id;
    String name;
    Integer taskId;
    Integer guestUserId;
    LocalDateTime createdAt;
}
