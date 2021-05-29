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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("findGuestUserUseCase Test")
@ExtendWith(MockitoExtension.class)
class FindGuestUserUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final GuestUserRepository guestUserRepository = mock(GuestUserRepository.class);

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
    @DisplayName("should return a list of guest users")
    void findGuestUserOk() {

        //given
        Integer roomId = 1;
        GuestUser guestUser = GuestUser.builder().name("Juan").roomId(roomId).build();

        when(guestUserRepository.findByRoom(roomId)).thenReturn(List.of(guestUser));

        FindGuestUserUseCase findGuestUserUseCase = new FindGuestUserUseCase(guestUserRepository);

        //when
        GuestUser resultGuestUser = findGuestUserUseCase.execute(roomId).get(0);

        //then
        assertEquals(guestUser, resultGuestUser);
    }

}
