package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Estimation;

public interface CreateFinalEstimationCommand {
    Integer execute(Estimation estimation);
}
