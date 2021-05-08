package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Task;

public interface TaskMessenger {
    void updated(Task task);
}
