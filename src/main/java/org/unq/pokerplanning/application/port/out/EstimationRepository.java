package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Estimation;

import java.util.List;
import java.util.Optional;

public interface EstimationRepository {
    List<Estimation> findByTask(Integer taskId);
    Optional<Estimation> get(Integer estimationId);
    Integer create(Estimation estimation);
    Integer invalidateAll(Integer taskId);
}
