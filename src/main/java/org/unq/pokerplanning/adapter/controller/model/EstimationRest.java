package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Estimation;

import java.time.LocalDateTime;

@Value
@Builder
public class EstimationRest {
    Integer id;
    String name;
    Integer taskId;
    Integer guestUserId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public static EstimationRest from(Estimation estimation) {
        return EstimationRest.builder()
                .id(estimation.getId())
                .name(estimation.getName())
                .taskId(estimation.getTaskId())
                .guestUserId(estimation.getGuestUserId())
                .createdAt(estimation.getCreatedAt())
                .build();
    }

    public Estimation toDomain() {
        return Estimation.builder()
                .name(this.name)
                .guestUserId(this.guestUserId)
                .taskId(this.taskId)
                .build();
    }
}
