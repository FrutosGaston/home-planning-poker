package org.unq.pokerplanning.adapter.websocket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.unq.pokerplanning.adapter.websocket.model.GuestUserWS;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.GuestUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("GuestUserWSAdapter Test")
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
public class GuestUserWSAdapterTest {

    @Mock
    private SimpMessagingTemplate templateMock;

    @InjectMocks
    private GuestUserWSAdapter guestUserWSAdapter;

    @Test
    @DisplayName("should send a guest user message on creation")
    void shouldConvertAndSendTheGuestUser()  {
        GuestUser guestUser = GuestUser.builder().build();

        doNothing().when(templateMock).convertAndSend(any(String.class), any(GuestUserWS.class));

        guestUserWSAdapter.created(guestUser);

        verify(templateMock, only()).convertAndSend(any(String.class), any(GuestUserWS.class));
    }
}
