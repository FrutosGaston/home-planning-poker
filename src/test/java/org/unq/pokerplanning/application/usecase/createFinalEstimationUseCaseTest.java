package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.application.service.EstimationService;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("createFinalEstimationUseCase Test")
@ExtendWith(MockitoExtension.class)
class createFinalEstimationUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final EstimationRepository estimationRepository = mock(EstimationRepository.class);
    private final TaskMessenger taskMessenger = mock(TaskMessenger.class);
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
    @DisplayName("When createFinalEstimation is executed Should Return its id")
    void createFinalEstimationOk() {

        //given
        Integer estimationId = 1;
        Estimation estimation = Estimation.builder().id(estimationId).cardId(1).taskId(1).guestUserId(1).build();
        Task task = Task.builder().id(1).build();

        when(estimationRepository.create(estimation)).thenReturn(1);
        when(taskRepository.get(estimation.getTaskId())).thenReturn(Optional.of(task));
        when(taskRepository.update(any(Task.class))).thenReturn(1);

        CreateFinalEstimationUseCase createFinalEstimationUseCase = new CreateFinalEstimationUseCase(estimationRepository, taskMessenger, taskRepository, estimationService);

        //when
        Integer resultEstimationId = createFinalEstimationUseCase.execute(estimation);

        //then
        verify(taskRepository, times(1)).update(any(Task.class));
        verify(taskMessenger, times(1)).estimated(task);
        assertEquals(1, resultEstimationId);
    }

}
