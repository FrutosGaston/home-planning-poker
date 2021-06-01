package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.*;
import org.unq.pokerplanning.domain.Room;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("updateRoomUseCase Test")
@ExtendWith(MockitoExtension.class)
class UpdateRoomUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomMessenger roomMessenger = mock(RoomMessenger.class);

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
    @DisplayName("When updateRoom is executed Should Return an integer indicating that it worked well")
    void updateRoomOk() {

        //given
        Integer updateResult = 1;
        Room room = Room.builder().build();

        when(roomRepository.update(room)).thenReturn(updateResult);
        when(roomRepository.getById(room.getId())).thenReturn(Optional.of(room));

        UpdateRoomUseCase updateRoomUseCase = new UpdateRoomUseCase(roomRepository, roomMessenger);

        //when
        Integer resultTask = updateRoomUseCase.execute(room);

        //then
        assertEquals(updateResult, resultTask);
        verify(roomMessenger, times(1)).updated(room);
    }

}
