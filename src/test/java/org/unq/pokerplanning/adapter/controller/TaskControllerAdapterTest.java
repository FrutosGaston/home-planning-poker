package org.unq.pokerplanning.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.unq.pokerplanning.adapter.controller.model.TaskRest;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.FindTaskQuery;
import org.unq.pokerplanning.application.port.in.UpdateTaskCommand;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("TaskController Adapter Test")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
class TaskControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateEstimationCommand createEstimationCommand;

    @MockBean
    private UpdateTaskCommand updateTaskCommand;

    @MockBean
    private FindTaskQuery findTaskQuery;

    private final Integer roomId = 1;
    private final String title = "Sprint 1";
    private final Integer taskId = 1;
    private final Integer guestUserId = 1;

    @Test
    @DisplayName("when createEstimation is called, the adapter must return its id")
    void createEstimation() throws Exception {
        Integer expectedId = 1;
        String bodyJson = "{\"taskId\":\"" + taskId + "\", \"name\":" + guestUserId +", \"guestUserId\":" + guestUserId + "}";
        when(createEstimationCommand.execute(getEstimation())).thenReturn(expectedId);
        this.mockMvc.perform(post("/api/v1/tasks/estimations")
                .content(bodyJson).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedId.toString()));
    }

    @Test
    @DisplayName("when patchTask is called, the adapter must return its id")
    void patchTask() throws Exception {
        String bodyJson = "{\"roomId\":\"" + roomId + "\", \"title\": \"" + title + "\"}";
        when(updateTaskCommand.execute(getTask())).thenReturn(1);
        this.mockMvc.perform(patch("/api/v1/tasks/" + taskId)
                .content(bodyJson).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when findTasks is called, the adapter must return a list of users")
    void findTasks() throws Exception {
        when(findTaskQuery.execute(roomId)).thenReturn(Collections.singletonList(getTask()));
        this.mockMvc.perform(get("/api/v1/tasks?roomId=" + roomId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(getTaskRest()))));
    }

    private Task getTask() {
        return Task.builder()
                .title(title)
                .roomId(roomId)
                .estimations(List.of())
                .build();
    }

    private TaskRest getTaskRest() {
        return TaskRest.builder()
                .title(title)
                .roomId(roomId)
                .estimations(List.of())
                .build();
    }

    private Estimation getEstimation() {
        return Estimation.builder()
                .taskId(taskId)
                .guestUserId(guestUserId)
                .cardId("1")
                .build();
    }
}
