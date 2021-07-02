package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.unq.pokerplanning.domain.GuestUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GuestUserVO {
    private Integer id;
    private String name;
    private Integer roomId;
    private Boolean spectator;

    public static GuestUserVO of(GuestUser guestUser) {
        return GuestUserVO.builder()
                .name(guestUser.getName())
                .roomId(guestUser.getRoomId())
                .spectator(guestUser.getSpectator())
                .build();
    }

    public GuestUser toDomain() {
        return GuestUser.builder()
                .id(this.id)
                .name(this.name)
                .roomId(this.roomId)
                .spectator(this.spectator)
                .build();
    }

    public MapSqlParameterSource toMap() {
        return new MapSqlParameterSource()
                .addValue("name", this.name)
                .addValue("spectator", this.spectator)
                .addValue("room_id", this.roomId);
    }
}
