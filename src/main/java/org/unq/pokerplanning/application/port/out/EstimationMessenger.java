package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Estimation;

public interface EstimationMessenger {
    void created(Estimation estimation, Integer roomId);
}
