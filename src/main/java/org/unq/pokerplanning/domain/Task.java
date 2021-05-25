package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Estimation> getEstimations() {
        return Optional.ofNullable(estimations).orElse(List.of());
    }
}
