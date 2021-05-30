package org.unq.pokerplanning.application.port.in;

public interface InvalidateEstimationsCommand {
    void execute(Integer taskId);
}
