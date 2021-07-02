package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class GuestUser {
    @With
    private Integer id;
    private String name;
    private Integer roomId;
    private Boolean spectator;
    private LocalDateTime createdAt;
}
