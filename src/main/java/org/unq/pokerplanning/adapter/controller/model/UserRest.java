package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.User;

@Value
@Builder
public class UserRest {
    Long id;
    String name;

    public static UserRest from(User user) {
        return UserRest.builder()
                .name(user.getName())
                .id(user.getId())
                .build();
    }

    public User toUser() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
