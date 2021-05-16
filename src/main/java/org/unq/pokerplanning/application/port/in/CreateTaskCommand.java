package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Task;

public interface CreateTaskCommand {
    Integer execute(Task estimation);
}
