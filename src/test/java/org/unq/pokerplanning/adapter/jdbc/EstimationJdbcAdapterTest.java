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
import org.unq.pokerplanning.config.TestConfig;
import org.unq.pokerplanning.domain.Estimation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("EstimationJdbcAdapter Test")
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
public class EstimationJdbcAdapterTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private  GenericDao genericDAO;

    @InjectMocks
    private EstimationJDBCAdapter estimationJDBCAdapter;


    @Test
    @DisplayName("When a estimation is created it should return the new id")
    void whenTheEstimationGetsCreatedItShouldReturnTheId()  {

        Integer estimationId = 1;
        Estimation estimation = Estimation.builder().build();

        when(genericDAO.insert(any(),any(),any())).thenReturn(estimationId);

        Integer createdEstimationId = estimationJDBCAdapter.create(estimation);

        assertEquals(estimationId, createdEstimationId);

        verify(genericDAO, only()).insert(any(),any(),any());
    }

    @Test
    @DisplayName("When it searches estimations by taskId it should return a list of estimations")
    void whenItSearchesEstimationsByTaskIDItShouldReturnAListOfEstimations()  {
        Integer taskId = 1;
        Integer estimationId = 1;
        EstimationVO estimationVO = EstimationVO.builder().id(estimationId).taskId(taskId).build();

        when(genericDAO.findObjects(any(),any(), any())).thenReturn(List.of(estimationVO));

        Estimation selectedEstimation = estimationJDBCAdapter.findByTask(taskId).get(0);

        assertEquals(estimationVO.getId(), selectedEstimation.getId());

        verify(genericDAO, only()).findObjects(any(),any(), any());
     }

}
