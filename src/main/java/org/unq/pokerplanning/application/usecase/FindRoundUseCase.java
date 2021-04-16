package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.FindRoundQuery;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.RoundRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Round;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class FindRoundUseCase implements FindRoundQuery {

    private final RoundRepository roundRepository;
    private final EstimationRepository estimationRepository;

    public FindRoundUseCase(RoundRepository roundRepository, EstimationRepository estimationRepository) {
        this.roundRepository = roundRepository;
        this.estimationRepository = estimationRepository;
    }

    @Override
    public Optional<Round> execute(Integer roomId) {
        Optional<Round> lastRound = roundRepository.findByRoom(roomId).stream()
                .max(Comparator.comparing(Round::getCreatedAt));
        return lastRound.map(round -> {
            List<Estimation> estimations = estimationRepository.findByRound(round.getId());
            round.setEstimations(estimations);
            return round;
        });
    }
}
