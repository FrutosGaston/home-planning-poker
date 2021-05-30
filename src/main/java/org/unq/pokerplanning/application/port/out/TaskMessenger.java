package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.List;

public interface TaskMessenger {
    void updated(Task task);
    void created(Task task);
    void estimated(Task task);
    void invalidated(Task task);
}
