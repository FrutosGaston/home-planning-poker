package org.unq.pokerplanning.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.GuestUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("RoomController Adapter Test")
@WebMvcTest(GuestUserControllerAdapter.class)
@Import(TestConfig.class)
class GuestGuestUserControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateGuestUserCommand createGuestUserCommand;

    @Test
    @DisplayName("when the addUserToRoom is called, the adapter must return a user")
    void addUserToRoom() throws Exception {
        Long roomId = 1L;
        String bodyJson = "{\"name\":\"Juan\", \"roomId\":1}";
        when(createGuestUserCommand.execute(getUser())).thenReturn(getUser());
        this.mockMvc.perform(post("/api/v1/guest-users")
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

    private GuestUser getUser() {
        return GuestUser.builder()
                .name("Juan")
                .roomId(1L)
                .build();
    }

    private GuestUserRest getUserRest() {
        return GuestUserRest.builder()
                .name("Juan")
                .roomId(1L)
                .build();
    }
}
