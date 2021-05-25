package org.unq.pokerplanning.application.service;

import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.NotFoundException;
import org.unq.pokerplanning.domain.Estimation;

@Component
public class EstimationService {

    private final EstimationRepository estimationRepository;
    private final CardRepository cardRepository;

    public EstimationService(EstimationRepository estimationRepository, CardRepository cardRepository) {
        this.estimationRepository = estimationRepository;
        this.cardRepository = cardRepository;
    }

    public Estimation getCompleteEstimation(Integer estimationId) {
        val estimation = estimationRepository.get(estimationId).orElseThrow(() -> new NotFoundException(ErrorCode.TYPE_NOT_FOUND));
        return completeEstimation(estimation);
    }

    public Estimation completeEstimation(Estimation estimation) {
        val card = cardRepository.get(estimation.getCardId()).orElse(null);
        return estimation.withCard(card);
    }
}
