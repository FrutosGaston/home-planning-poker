package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Round {
    private Integer id;
    private Integer roomId;
    private String title;
    private List<Estimation> estimations;
    private LocalDateTime createdAt;
}
