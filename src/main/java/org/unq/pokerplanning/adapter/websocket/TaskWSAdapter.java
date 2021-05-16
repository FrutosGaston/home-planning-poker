package org.unq.pokerplanning.adapter.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.websocket.model.TaskWS;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.domain.Task;

@Component
@Slf4j
public class TaskWSAdapter implements TaskMessenger {

    final private SimpMessageSendingOperations template;

    public TaskWSAdapter(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void updated(Task task) {
        Integer roomId = task.getRoomId();
        log.info("sending task updated event to room: {} and task: {}", roomId, task);
        template.convertAndSend(
                String.format("/room/%s/tasks/updated", roomId),
                TaskWS.of(task));
    }

    public void created(Task task) {
        Integer roomId = task.getRoomId();
        log.info("sending task created event to room: {} and task: {}", roomId, task);
        template.convertAndSend(
                String.format("/room/%s/tasks/created", roomId),
                TaskWS.of(task));
    }

}
