package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
    private Integer estimationId;
    private Integer roomId;
    private LocalDateTime createdAt;

    public static TaskVO of(Task task) {
        return TaskVO.builder()
                .id(task.getId())
                .estimationId(task.getEstimationId())
                .title(task.getTitle())
                .roomId(task.getRoomId())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public Task toDomain() {
        return Task.builder()
                .id(this.id)
                .title(this.title)
                .estimationId(this.estimationId)
                .roomId(this.roomId)
                .createdAt(this.createdAt)
                .build();
    }

    public MapSqlParameterSource toCreateMap() {
        return new MapSqlParameterSource()
                .addValue("id", this.id)
                .addValue("title", this.title)
                .addValue("room_id", this.roomId);
    }

    public MapSqlParameterSource toUpdateMap() {
        return new MapSqlParameterSource()
                .addValue("id", this.id)
                .addValue("title", this.title)
                .addValue("estimation_id", this.estimationId);
    }
}
