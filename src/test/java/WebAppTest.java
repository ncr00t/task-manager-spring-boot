import com.company.tasks.models.Task;
import com.company.tasks.services.TaskService;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class WebAppTest extends ApplicationTest{

    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void if_valid_request_for_get_all_tasks_then_status_request_should_be_ok() throws Exception {
        Collection<Task> getAllTasksResult = new ArrayList<>();
        doReturn(getAllTasksResult).when(taskService).findAllTasks();

        String validRequestForGetAllTasks = "/tasks";
        ResultActions actionsForGetAllTasks = mockMvc.perform(get(validRequestForGetAllTasks));
        actionsForGetAllTasks
                             .andExpect(
                                        status()
                                                .isOk());

    }

    @Test
    public void if_invalid_request_for_get_all_tasks_then_status_request_should_be_not_found() throws Exception {
        Collection<Task> getAllTasksResult = new ArrayList<>();
        doReturn(getAllTasksResult).when(taskService).findAllTasks();

        String invaidRequestForGetAllTasks ="/taskss";
        ResultActions actionsForGetAllTasks = mockMvc.perform(get(invaidRequestForGetAllTasks));
        actionsForGetAllTasks
                             .andExpect(
                                         status()
                                                 .isNotFound());

    }

    @Test
    public void if_valid_request_for_get_completed_tasks_then_status_request_should_be_ok() throws Exception {
        Collection<Task> getCompletedTasksResult = new ArrayList<>();
        doReturn(getCompletedTasksResult).when(taskService).findAllCompletedTasks();

        String validRequestForGetCompletedTasks = "/completedTasks";
        ResultActions actionsForGetCompletedTasks = mockMvc.perform(get(validRequestForGetCompletedTasks));
        actionsForGetCompletedTasks
                                   .andExpect(
                                              status()
                                                      .isOk());

    }

    @Test
    public void if_invalid_request_for_get_completed_tasks_then_status_request_should_be_not_found() throws Exception {
        Collection<Task> getCompletedTasksResult = new ArrayList<>();
        doReturn(getCompletedTasksResult).when(taskService).findAllCompletedTasks();

        String invalidRequestForGetCompletedTasks ="/completedTaskss";
        ResultActions actionsForGetCompletedTasks = mockMvc.perform(get(invalidRequestForGetCompletedTasks));
        actionsForGetCompletedTasks
                                    .andExpect(
                                               status()
                                                       .isNotFound());

    }

    @Test
    public void if_valid_request_for_get_tasks_in_work_then_status_request_should_be_ok() throws Exception {
        Collection<Task> getTasksInWorkResult = new ArrayList<>();
        doReturn(getTasksInWorkResult).when(taskService).findAllTasksInWork();

        String validRequestForGetTasksInWork = "/tasksInWork";
        ResultActions actionsForGetTasksInWork = mockMvc.perform(get(validRequestForGetTasksInWork));
        actionsForGetTasksInWork
                                .andExpect(
                                           status()
                                                   .isOk());

    }

    @Test
    public void if_invalid_request_for_get_tasks_in_work_then_status_request_should_be_not_found() throws Exception {
        Collection<Task> getTasksInWorkResult = new ArrayList<>();
        doReturn(getTasksInWorkResult).when(taskService).findAllTasksInWork();

        String invalidRequestForGetTasksInWork = "/tasksInWorkk";
        ResultActions actionsForGetTasksInWork = mockMvc.perform(get(invalidRequestForGetTasksInWork));
        actionsForGetTasksInWork
                                .andExpect(
                                            status()
                                                    .isNotFound());

    }

    @Test
    public void if_valid_request_for_show_statistics_complete_then_status_request_should_be_ok() throws Exception{
        String validRequestForShowStatisticsComplete = "/statisticsComplete";
        mockMvc.perform(get(validRequestForShowStatisticsComplete))
               .andExpect(
                          status()
                                  .isOk());
    }

    @Test
    public void if_invalid_request_for_show_statistics_complete_then_status_request_should_be_not_found() throws Exception{
        String invalidRequestForShowStatisticsComplete = "/statisticsCompletee";
        mockMvc.perform(get(invalidRequestForShowStatisticsComplete))
               .andExpect(
                          status()
                                  .isNotFound());
    }

    @Test
    public void if_valid_request_for_show_statisticsByStagesComplete_then_status_request_should_be_ok() throws Exception{
        String validRequestForShowStatisticsComplete = "/statisticsByStagesComplete";
        mockMvc.perform(get(validRequestForShowStatisticsComplete))
                .andExpect(
                             status()
                                     .isOk());
    }

    @Test
    public void if_invalid_request_for_show_statisticsByStagesComplete_then_status_request_should_be_not_found() throws Exception{
        String invalidRequestForShowStatisticsComplete = "/statisticsByStagesCompletee";
        mockMvc.perform(get(invalidRequestForShowStatisticsComplete))
                .andExpect(
                            status()
                                    .isNotFound());
    }
}
