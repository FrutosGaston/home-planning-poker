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
    private String name;
    private Long roomId;

    public static GuestUserVO of(GuestUser guestUser) {
        return GuestUserVO.builder()
                .name(guestUser.getName())
                .roomId(guestUser.getRoomId())
                .build();
    }

    public GuestUser toDomain() {
        return GuestUser.builder()
                .name(this.name)
                .build();
    }

    public MapSqlParameterSource toMap() {
        return new MapSqlParameterSource()
                .addValue("name", this.name)
                .addValue("room_id", this.roomId);
    }
}
