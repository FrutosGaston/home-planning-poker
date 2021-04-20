package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Task;

public interface UpdateTaskCommand {
    Integer execute(Task task);
}
