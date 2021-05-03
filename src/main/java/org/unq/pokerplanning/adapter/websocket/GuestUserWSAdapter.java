package org.unq.pokerplanning.adapter.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.websocket.model.GuestUserWS;
import org.unq.pokerplanning.application.port.out.GuestUserMessenger;
import org.unq.pokerplanning.domain.GuestUser;

@Component
@Slf4j
public class GuestUserWSAdapter implements GuestUserMessenger {

    final private SimpMessageSendingOperations template;

    public GuestUserWSAdapter(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void created(GuestUser guestUser) {
        Integer roomId = guestUser.getRoomId();
        log.info("sending guest user created event to room: {} and guest: {}", roomId, guestUser);
        template.convertAndSend(
                String.format("/room/%s/guest-users/created", roomId),
                GuestUserWS.from(guestUser));
    }

}
