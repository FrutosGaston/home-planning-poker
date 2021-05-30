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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("invalidateEstimationUseCase Test")
@ExtendWith(MockitoExtension.class)
class InvalidateEstimationsUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final EstimationRepository estimationRepository = mock(EstimationRepository.class);
    private final TaskMessenger taskMessenger = mock(TaskMessenger.class);
    private final TaskRepository taskRepository = mock(TaskRepository.class);

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
    @DisplayName("When invalidateEstimations is executed Should Return its id")
    void invalidateEstimationOk() {

        //given
        Integer estimationId = 1;
        Integer taskId = 1;
        Estimation estimation = Estimation.builder().id(estimationId).cardId(1).taskId(1).guestUserId(1).build();
        Task task = Task.builder().id(taskId).estimations(List.of(estimation)).build();

        when(estimationRepository.invalidateAll(taskId)).thenReturn(1);
        when(estimationRepository.findByTask(taskId)).thenReturn(List.of(estimation));
        when(taskRepository.get(taskId)).thenReturn(Optional.of(task));

        InvalidateEstimationsUseCase invalidateEstimationsUseCase = new InvalidateEstimationsUseCase(taskMessenger, estimationRepository, taskRepository);

        //when
        invalidateEstimationsUseCase.execute(taskId);

        //then
        verify(estimationRepository, times(1)).invalidateAll(taskId);
        verify(estimationRepository, times(1)).findByTask(taskId);
        verify(taskRepository, times(1)).get(taskId);
        verify(taskMessenger, times(1)).invalidated(task);
    }

}
