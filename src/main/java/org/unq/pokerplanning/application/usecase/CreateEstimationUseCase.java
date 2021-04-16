package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.GuestUser;

@Component
@Slf4j
public class CreateEstimationUseCase implements CreateEstimationCommand {

    private final EstimationRepository estimationRepository;

    public CreateEstimationUseCase(EstimationRepository estimationRepository) {
        this.estimationRepository = estimationRepository;
    }

    @Override
    public Integer execute(Estimation estimation) {
        return estimationRepository.create(estimation);
    }
}
