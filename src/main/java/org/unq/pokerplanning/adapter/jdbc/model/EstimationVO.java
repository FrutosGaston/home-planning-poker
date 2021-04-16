package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Round;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EstimationVO {
    private Integer id;
    private String name;
    private Integer roundId;
    private Integer guestUserId;
    private LocalDateTime createdAt;

    public Estimation toDomain() {
        return Estimation.builder()
                .id(this.id)
                .name(this.name)
                .roundId(this.roundId)
                .guestUserId(this.guestUserId)
                .createdAt(this.createdAt)
                .build();
    }

}
