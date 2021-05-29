package org.unq.pokerplanning.application.usecase;

import org.unq.pokerplanning.application.port.out.GuestUserMessenger;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.GuestUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("AddUserToRoomUseCase Test")
@ExtendWith(MockitoExtension.class)
class CreateGuestUserUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final GuestUserRepository guestUserRepository = mock(GuestUserRepository.class);
    private final GuestUserMessenger guestUserMessenger = mock(GuestUserMessenger.class);

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
    @DisplayName("When createGuestUser is executed Should Return its id")
    void createGuestUserOk() {

        //given
        GuestUser guestUser = GuestUser.builder().name("Juan").roomId(1).build();

        when(guestUserRepository.createGuestUser(guestUser)).thenReturn(1);

        CreateGuestUserUseCase createGuestUserUseCase = new CreateGuestUserUseCase(guestUserRepository, guestUserMessenger);

        //when
        Integer resultGuestUser = createGuestUserUseCase.execute(guestUser);

        //then
        assertEquals(1, resultGuestUser);
    }

    @Test
    @DisplayName("guest users messenger should be called")
    void guestUserMessengerShouldBeCalled() {
        //given
        Integer newId = 1;
        GuestUser guestUser = GuestUser.builder().name("Juan").roomId(1).build();
        when(guestUserRepository.createGuestUser(guestUser)).thenReturn(newId);
        CreateGuestUserUseCase createGuestUserUseCase = new CreateGuestUserUseCase(guestUserRepository, guestUserMessenger);

        //when
        createGuestUserUseCase.execute(guestUser);

        //then
        GuestUser createdUser = guestUser.withId(newId);
        verify(guestUserMessenger).created(createdUser);
    }

}
