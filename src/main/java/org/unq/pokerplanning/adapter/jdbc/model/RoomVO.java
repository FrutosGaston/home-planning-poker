package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.unq.pokerplanning.domain.Room;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoomVO {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    public static RoomVO of(Room room) {
        return RoomVO.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .createdAt(room.getCreatedAt())
                .build();
    }

    public Room toDomain() {
        return Room.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .createdAt(this.createdAt)
                .build();
    }

    public MapSqlParameterSource toMap() {
        return new MapSqlParameterSource()
                .addValue("title", this.title)
                .addValue("description", this.description);
    }
}
