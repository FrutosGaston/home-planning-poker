package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Task;

import java.util.List;

public interface FindTaskQuery {
    List<Task> execute(Integer roomId);
}
