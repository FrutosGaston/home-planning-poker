package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class Room {
    Integer id;
    String title;
    String description;
    LocalDateTime createdAt;
}
