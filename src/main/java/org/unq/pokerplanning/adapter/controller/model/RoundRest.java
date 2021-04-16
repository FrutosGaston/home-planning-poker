package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Round;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class RoundRest {
    Integer id;
    Integer roomId;
    String title;
    List<EstimationRest> estimations;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public static RoundRest from(Round round) {
        return RoundRest.builder()
                .id(round.getId())
                .roomId(round.getRoomId())
                .title(round.getTitle())
                .createdAt(round.getCreatedAt())
                .estimations(round.getEstimations().stream()
                        .map(EstimationRest::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
