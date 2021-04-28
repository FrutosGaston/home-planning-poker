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
import org.unq.pokerplanning.adapter.jdbc.model.EstimationVO;
import org.unq.pokerplanning.adapter.jdbc.model.TaskVO;
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("TaskJdbcAdapter Test")
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
public class TaskJdbcAdapterTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private  GenericDao genericDAO;

    @InjectMocks
    private TaskJDBCAdapter taskJDBCAdapter;


    @Test
    @DisplayName("When a task is update it should return the amount of rows updated")
    void whenATaskGetsUpdatedItShouldReturnTheAmountOfRowsUpdated()  {

        Integer updateResult = 1;
        Task task = Task.builder().build();

        when(genericDAO.updateObject(any(), any())).thenReturn(updateResult);

        Integer updatedTaskResult = taskJDBCAdapter.update(task);

        assertEquals(updateResult, updatedTaskResult);

        verify(genericDAO, only()).updateObject(any(), any());
    }

    @Test
    @DisplayName("When it searches tasks by roomId it should return a list of tasks")
    void whenItSearchesTasksByRoomIDItShouldReturnAListOfTasks()  {
        Integer taskId = 1;
        Integer roomId = 1;
        TaskVO taskVO = TaskVO.builder().roomId(roomId).id(taskId).build();

        when(genericDAO.findObjects(any(),any(), any())).thenReturn(List.of(taskVO));

        Task selectedTask = taskJDBCAdapter.findByRoom(roomId).get(0);

        assertEquals(taskVO.getId(), selectedTask.getId());

        verify(genericDAO, only()).findObjects(any(),any(), any());
     }

}
