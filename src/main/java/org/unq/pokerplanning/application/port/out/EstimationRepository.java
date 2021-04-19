package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Estimation;

import java.util.List;

public interface EstimationRepository {
    List<Estimation> findByTask(Integer taskId);

    Integer create(Estimation estimation);
}
