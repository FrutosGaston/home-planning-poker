package org.unq.pokerplanning.application.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.GuestUserMessenger;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("AddUserToRoomUseCase Test")
@ExtendWith(MockitoExtension.class)
class CreateRoomUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final RoomRepository roomRepository = mock(RoomRepository.class);

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
    @DisplayName("When createRoom is executed Should Return its id")
    void createRoomOk() {

        //given
        Room room = Room.builder().title("Sprint 1").build();

        when(roomRepository.create(room)).thenReturn(1);

        CreateRoomUseCase createRoomUseCase = new CreateRoomUseCase(roomRepository);

        //when
        Integer resultGuestUser = createRoomUseCase.execute(room);

        //then
        assertEquals(1, resultGuestUser);
    }

}
