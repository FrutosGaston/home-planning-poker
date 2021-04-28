package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("findTaskUseCase Test")
@ExtendWith(MockitoExtension.class)
class findTaskUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final TaskRepository taskRepository = mock(TaskRepository.class);
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
    @DisplayName("When findTask is executed Should Return a list of tasks with their estimations")
    void findTaskOk() {

        //given
        Integer roomId = 1;
        Integer taskId = 1;
        Task task = Task.builder().id(taskId).title("task 1").roomId(roomId).build();
        Estimation estimation = Estimation.builder().taskId(taskId).build();

        when(taskRepository.findByRoom(roomId)).thenReturn(List.of(task));
        when(estimationRepository.findByTask(taskId)).thenReturn(List.of(estimation));

        FindTaskUseCase findTaskUseCase = new FindTaskUseCase(taskRepository, estimationRepository);

        //when
        Task resultTask = findTaskUseCase.execute(roomId).get(0);

        //then
        assertEquals(task.getId(), resultTask.getId());
        assertEquals(estimation, resultTask.getEstimations().get(0));
    }

}
