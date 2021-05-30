package org.unq.pokerplanning.adapter.websocket.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Card;
import org.unq.pokerplanning.domain.Estimation;

import java.time.LocalDateTime;

@Value
@Builder
public class EstimationWS {
    Integer id;
    Card card;
    Integer taskId;
    Integer guestUserId;
    LocalDateTime createdAt;
    Boolean active;

    public static EstimationWS of(Estimation estimation) {
        return EstimationWS.builder()
                .id(estimation.getId())
                .card(estimation.getCard())
                .taskId(estimation.getTaskId())
                .guestUserId(estimation.getGuestUserId())
                .active(estimation.getActive())
                .build();
    }
}
