package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Room;
import org.unq.pokerplanning.domain.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AddUserToRoomUseCase Test")
@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final TaskRepository taskRepository = mock(TaskRepository.class);
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
    @DisplayName("When createTask is executed Should Return its id")
    void createTaskOk() {

        //given
        Task task = Task.builder().title("Una tarea").build();

        when(taskRepository.create(task)).thenReturn(1);

        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(taskMessenger, taskRepository);

        //when
        Integer resultGuestUser = createTaskUseCase.execute(task);

        //then
        assertEquals(1, resultGuestUser);
    }

}
