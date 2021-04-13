package org.unq.pokerplanning.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.unq.pokerplanning.adapter.controller.model.UserRest;
import org.unq.pokerplanning.adapter.rest.exception.NotFoundRestClientException;
import org.unq.pokerplanning.application.port.in.AddUserToRoomCommand;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("RoomController Adapter Test")
@WebMvcTest(RoomControllerAdapter.class)
@Import(TestConfig.class)
class RoomControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddUserToRoomCommand addUserToRoomCommand;

    @Test
    @DisplayName("when the addUserToRoom is called, the adapter must return a user")
    void addUserToRoom() throws Exception {
        Long roomId = 1L;
        String bodyJson = "{\"name\":\"Juan\"}";
        when(addUserToRoomCommand.execute(getUser(), roomId)).thenReturn(getUser());
        this.mockMvc.perform(post("/api/v1/rooms/{roomId}/users", roomId)
                .content(bodyJson).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(getUserRest())));
    }

    @Test
    @DisplayName("when the getPokemon is called, the adapter must return a NotFound exception")
    void getPokemonNotFound() throws Exception {
//
//        when(getPokemonAbilityQuery.getPokemon(anyString()))
//                .thenThrow(new NotFoundRestClientException(ErrorCode.POKEMON_NOT_FOUND));
//        this.mockMvc.perform(get("/api/v1/pokemon/{name}", PIKACHU))
//                .andDo(print())
//                .andExpect(status().isNotFound())
//                .andExpect(content().string(containsString(ErrorCode.POKEMON_NOT_FOUND.getReasonPhrase())));
    }

    private User getUser() {
        return User.builder()
                .name("Juan")
                .build();
    }

    private UserRest getUserRest() {
        return UserRest.builder()
                .name("Juan")
                .build();
    }
}
