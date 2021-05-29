package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.application.service.EstimationService;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("createEstimationUseCase Test")
@ExtendWith(MockitoExtension.class)
class CreateEstimationUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final EstimationRepository estimationRepository = mock(EstimationRepository.class);
    private final EstimationMessenger estimationMessenger = mock(EstimationMessenger.class);
    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final EstimationService estimationService = mock(EstimationService.class);

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
        Integer estimationId = 1;
        Integer roomId = 1;
        Estimation estimation = Estimation.builder().cardId(1).taskId(1).guestUserId(1).build();
        Estimation estimationWithId = Estimation.builder().id(estimationId).cardId(1).taskId(1).guestUserId(1).build();
        Task task = Task.builder().id(1).roomId(roomId).build();

        when(estimationRepository.create(estimation)).thenReturn(estimationId);
        when(taskRepository.get(estimation.getTaskId())).thenReturn(Optional.of(task));
        when(estimationService.completeEstimation(estimationWithId)).thenReturn(estimationWithId);

        CreateEstimationUseCase createEstimationUseCase = new CreateEstimationUseCase(estimationRepository, estimationMessenger, taskRepository, estimationService);

        //when
        Integer resultEstimationId = createEstimationUseCase.execute(estimation);

        //then
        verify(estimationMessenger, times(1)).created(estimation.withId(estimationId), roomId);
        assertEquals(1, resultEstimationId);
    }

}
