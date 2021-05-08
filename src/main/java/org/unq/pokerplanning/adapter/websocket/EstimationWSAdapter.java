package org.unq.pokerplanning.adapter.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.websocket.model.EstimationWS;
import org.unq.pokerplanning.adapter.websocket.model.TaskWS;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

@Component
@Slf4j
public class EstimationWSAdapter implements EstimationMessenger {

    final private SimpMessageSendingOperations template;

    public EstimationWSAdapter(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void created(Estimation estimation, Integer roomId) {
        log.info("sending estimation created event to room: {} and estimation: {}", roomId, estimation);
        template.convertAndSend(
                String.format("/room/%s/estimations/created", roomId),
                EstimationWS.of(estimation));
    }

}
