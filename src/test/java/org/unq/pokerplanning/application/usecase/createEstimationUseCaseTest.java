package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.GuestUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("createEstimationUseCase Test")
@ExtendWith(MockitoExtension.class)
class createEstimationUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final EstimationRepository estimationRepository = mock(EstimationRepository.class);

    @BeforeAll
    static void init() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN);
        executor.setThreadNamePrefix(ASYNC_PREFIX);
        executor.initialize();
    }

    @Test
    @DisplayName("When createEstimation is executed Should Return its id")
    void createEstimationOk() {

        //given
        Estimation estimation = Estimation.builder().name("1").taskId(1).guestUserId(1).build();

        when(estimationRepository.create(estimation)).thenReturn(1);

        CreateEstimationUseCase createEstimationUseCase = new CreateEstimationUseCase(estimationRepository);

        //when
        Integer resultEstimationId = createEstimationUseCase.execute(estimation);

        //then
        assertEquals(1, resultEstimationId);
    }

}
