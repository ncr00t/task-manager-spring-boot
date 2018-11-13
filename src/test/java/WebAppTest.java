import com.company.tasks.controllers.TasksController;
import com.company.tasks.models.Task;
import com.company.tasks.services.TaskService;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class WebAppControllerTest extends ApplicationTest{
    @MockBean
    private TaskService taskService;
    private TasksController tasksController;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        tasksController = new TasksController(taskService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void taskController_should_be_not_null(){
        assertNotNull(tasksController);
    }

    @Test
    public void testGetPerson() throws Throwable {
        // When
        int id = 0;
        ResultActions actions = mockMvc.perform(get("/updateTask?id=" + id));

        // Then
        actions.andExpect(status().isOk());
    }

    @Test
    public void testTasks() throws Exception {
        Collection<Task> getAllTasksResult = new ArrayList<>();
        doReturn(getAllTasksResult).when(taskService).findAllTasks();
        ResultActions actions = mockMvc.perform(get("/tasks"));

    }
}
