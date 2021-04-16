package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Estimation {
    private Integer id;
    private String name;
    private Integer roundId;
    private Integer guestUserId;
    private LocalDateTime createdAt;
}
