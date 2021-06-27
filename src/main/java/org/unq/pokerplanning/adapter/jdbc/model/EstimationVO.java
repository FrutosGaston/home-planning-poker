package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.unq.pokerplanning.domain.Estimation;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EstimationVO {
    private Integer id;
    private Integer cardId;
    private Integer taskId;
    private Integer guestUserId;
    private LocalDateTime createdAt;
    private Boolean active;

    public static EstimationVO of(Estimation estimation) {
        return EstimationVO.builder()
                .cardId(estimation.getCardId())
                .taskId(estimation.getTaskId())
                .guestUserId(estimation.getGuestUserId())
                .build();
    }

    public Estimation toDomain() {
        return Estimation.builder()
                .id(this.id)
                .cardId(this.cardId)
                .taskId(this.taskId)
                .guestUserId(this.guestUserId)
                .createdAt(this.createdAt)
                .active(this.active)
                .build();
    }

    public MapSqlParameterSource toMap() {
        return new MapSqlParameterSource()
                .addValue("card_id", this.cardId)
                .addValue("guest_user_id", this.guestUserId)
                .addValue("task_id", this.taskId);
    }

    public MapSqlParameterSource toInvalidateMap() {
        return new MapSqlParameterSource()
                .addValue("task_id", this.taskId)
                .addValue("active", false);
    }

    public MapSqlParameterSource toInvalidateByGuestUserMap() {
        return new MapSqlParameterSource()
                .addValue("task_id", this.taskId)
                .addValue("guest_user_id", this.guestUserId)
                .addValue("active", false);
    }
}
