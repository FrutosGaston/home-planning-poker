package org.unq.pokerplanning.adapter.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.websocket.model.EstimationWS;
import org.unq.pokerplanning.adapter.websocket.model.RoomWS;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.RoomMessenger;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Room;

@Component
@Slf4j
public class RoomWSAdapter implements RoomMessenger {

    final private SimpMessageSendingOperations template;

    public RoomWSAdapter(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void updated(Room room) {
        log.info("sending room updated event to room: {}", room);
        template.convertAndSend(
                String.format("/room/%s/updated", room.getId()),
                RoomWS.of(room));
    }

}
