package org.unq.pokerplanning.adapter.websocket.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.adapter.jdbc.model.EstimationVO;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;

@Value
@Builder
public class EstimationWS {
    Integer id;
    String name;
    Integer taskId;
    Integer guestUserId;
    LocalDateTime createdAt;

    public static EstimationWS of(Estimation estimation) {
        return EstimationWS.builder()
                .id(estimation.getId())
                .name(estimation.getName())
                .taskId(estimation.getTaskId())
                .guestUserId(estimation.getGuestUserId())
                .build();
    }
}
