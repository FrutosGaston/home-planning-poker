package org.unq.pokerplanning.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.application.port.in.FindGuestUserQuery;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.GuestUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("GuestUserController Adapter Test")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
class GuestUserControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateGuestUserCommand createGuestUserCommand;

    @MockBean
    private FindGuestUserQuery findGuestUserQuery;

    Integer roomId = 1;
    String name = "Juan";

    @Test
    @DisplayName("when createGuestUser is called, the adapter must return its id")
    void createGuestUser() throws Exception {
        Integer expectedId = 1;
        String bodyJson = "{\"name\":\"" + name + "\", \"roomId\":" + roomId + "}";
        when(createGuestUserCommand.execute(getUser())).thenReturn(expectedId);
        this.mockMvc.perform(post("/api/v1/guest-users")
                .content(bodyJson).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedId.toString()));
    }

    @Test
    @DisplayName("when findGuestUser is called, the adapter must return a list of users")
    void findGuestUser() throws Exception {
        when(findGuestUserQuery.execute(roomId)).thenReturn(Collections.singletonList(getUser()));
        this.mockMvc.perform(get("/api/v1/guest-users?roomId=" + roomId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(getUserRest()))));
    }

    private GuestUser getUser() {
        return GuestUser.builder()
                .name(name)
                .roomId(roomId)
                .build();
    }

    private GuestUserRest getUserRest() {
        return GuestUserRest.builder()
                .name(name)
                .roomId(roomId)
                .build();
    }
}
