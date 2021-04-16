package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Round;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoundVO {
    private Integer id;
    private String title;
    private Integer roomId;
    private LocalDateTime createdAt;

    public Round toDomain() {
        return Round.builder()
                .id(this.id)
                .title(this.title)
                .roomId(this.roomId)
                .createdAt(this.createdAt)
                .build();
    }

}
