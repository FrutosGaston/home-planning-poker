package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GuestUser {
    private Long id;
    private String name;
    private Long roomId;
}
