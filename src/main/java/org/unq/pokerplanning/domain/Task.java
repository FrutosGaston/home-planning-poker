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
    private Integer id;
    private Integer roomId;
    private String title;
    @With private List<Estimation> estimations;
    private LocalDateTime createdAt;
}
