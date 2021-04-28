package org.unq.pokerplanning.adapter.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.unq.pokerplanning.adapter.jdbc.model.GuestUserVO;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("GuestUserJdbcAdapter Test")
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
public class GuestUserJdbcAdapterTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private  GenericDao genericDAO;

    @InjectMocks
    private GuestUserJDBCAdapter guestUserJDBCAdapter;


    @Test
    @DisplayName("When a guest user is created it should return the new id")
    void whenTheGuestUserGetsCreatedItShouldReturnTheId()  {

        Integer guestUserId = 1;
        GuestUser guestUser = GuestUser.builder().build();

        when(genericDAO.insert(any(),any(),any())).thenReturn(guestUserId);

        Integer createdGuestUserId = guestUserJDBCAdapter.createGuestUser(guestUser);

        assertEquals(guestUserId, createdGuestUserId);

        verify(genericDAO, only()).insert(any(),any(),any());
    }

    @Test
    @DisplayName("When it searches guest users by roomId it should return a list of guest users")
    void whenItSearchesGuestUsersByRoomIDItShouldReturnAListOfGuestUsers()  {
        Integer roomId = 1;
        Integer estimationId = 1;
        GuestUserVO estimationVO = GuestUserVO.builder().id(estimationId).roomId(roomId).build();

        when(genericDAO.findObjects(any(),any(), any())).thenReturn(List.of(estimationVO));

        GuestUser selectedGuestUser = guestUserJDBCAdapter.findByRoom(roomId).get(0);

        assertEquals(estimationVO.getId(), selectedGuestUser.getId());

        verify(genericDAO, only()).findObjects(any(),any(), any());
     }

}
