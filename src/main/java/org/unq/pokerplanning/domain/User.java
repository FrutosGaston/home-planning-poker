package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class User {
    private Long id;
    private String name;
}
