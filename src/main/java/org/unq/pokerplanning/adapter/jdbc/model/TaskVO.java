package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TaskVO {
    private Integer id;
    private String title;
    private String finalEstimation;
    private Integer roomId;
    private LocalDateTime createdAt;

    public Task toDomain() {
        return Task.builder()
                .id(this.id)
                .title(this.title)
                .finalEstimation(this.finalEstimation)
                .roomId(this.roomId)
                .createdAt(this.createdAt)
                .build();
    }

}
