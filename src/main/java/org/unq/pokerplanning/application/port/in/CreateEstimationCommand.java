package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Estimation;
public interface CreateEstimationCommand {
    Integer execute(Estimation estimation);
}
