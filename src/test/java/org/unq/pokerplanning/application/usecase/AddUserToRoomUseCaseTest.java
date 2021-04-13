package org.unq.pokerplanning.application.usecase;

import org.unq.pokerplanning.adapter.controller.model.UserRest;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AddUserToRoomUseCase Test")
@ExtendWith(MockitoExtension.class)
class AddUserToRoomUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;
    private static ThreadPoolTaskExecutor executor;

    private GuestUserRepository guestUserRepository = mock(GuestUserRepository.class);

    @BeforeAll
    static void init() {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN);
        executor.setThreadNamePrefix(ASYNC_PREFIX);
        executor.initialize();
    }

    @Test
    @DisplayName("When GetPokemonAbilityUseCase is executed Should Return a Pokemon")
    void testPokemonAbility() throws ExecutionException, InterruptedException {

        //given
        User user = User.builder().name("Juan").build();

        when(guestUserRepository.createGuestUser(user, 1L)).thenReturn(1);

        AddUserToRoomUseCase addUserToRoomUseCase = new AddUserToRoomUseCase(guestUserRepository);

        //when
        User resultUser = addUserToRoomUseCase.execute(user, 1L);

        //then
        assertEquals(User.builder().name("Juan").build(), resultUser);
    }

}
