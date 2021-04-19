package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Builder
public class Estimation {
    private Integer id;
    private String name;
    private Integer taskId;
    private Integer guestUserId;
    private LocalDateTime createdAt;
}
