package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Task {
    @With
    Integer id;

    Integer roomId;
    String title;
    Integer estimationId;
    @With Estimation estimation;
    @With List<Estimation> estimations;
    LocalDateTime createdAt;
}
