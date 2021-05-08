package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("updateTaskUseCase Test")
@ExtendWith(MockitoExtension.class)
class updateTaskUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final EstimationRepository estimationRepository = mock(EstimationRepository.class);
    private final TaskMessenger taskMessenger = mock(TaskMessenger.class);

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
    @DisplayName("When updateTask is executed Should Return an integer indicating that it worked well")
    void updateTaskOk() {

        //given
        Integer updateResult = 1;
        Task task = Task.builder().build();

        when(taskRepository.update(task)).thenReturn(updateResult);

        UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(taskRepository, taskMessenger, estimationRepository);

        //when
        Integer resultTask = updateTaskUseCase.execute(task);

        //then
        assertEquals(updateResult, resultTask);
    }

}
