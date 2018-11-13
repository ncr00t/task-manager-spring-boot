import com.company.tasks.models.Task;
import com.company.tasks.services.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TaskServiceTest{

    @MockBean
    private TaskService taskService;

    @Test
    public void taskService_should_be_not_null(){
        assertNotNull(taskService);
    }

    @Test
    public void if_tasks_found_by_name_then_return_list(){
        String taskName = "TaskName";
        Task task = new Task(       taskName,
                        "Description",
                                    new Date(01/01/2018));
        when(taskService.getTaskByName(taskName)).thenReturn(singletonList(task));

        assertTasksFoundByName(taskName, task);
    }

    @Test
    public void if_tasks_not_found_by_name_then_return_empty_list(){
        String taskName = "someName";
        when(taskService.getTaskByName(taskName)).thenReturn(emptyList());

        assertTasksNotFoundByName(taskName);

        verify(taskService, only()).getTaskByName(taskName);
    }

    @Test
    public void if_tasks_foound_by_date_then_return_list(){
        Date dateCreated = new Date(01/01/2018);

        Task task = new Task(  "TaskName",
                           "Description",
                                      dateCreated
                            );
        when(taskService.getTaskByDateCreated(dateCreated)).thenReturn(singletonList(task));

        assertTasksFoundByDateCreated(dateCreated, task);
    }

    @Test
    public void if_tasks_not_found_by_date_then_return_empty_list(){
        Date dateCreated = new Date(01/01/2018);

        when(taskService.getTaskByDateCreated(dateCreated)).thenReturn(emptyList());

        assertTasksNotFoundByDateCreated(dateCreated);

        verify(taskService, only()).getTaskByDateCreated(dateCreated);
    }

    private void assertTasksFoundByName(String taskName, Task task){
        assertThat(taskService.getTaskByName(taskName), hasItem(task));
    }

    private void assertTasksNotFoundByName(String taskName){
        assertThat(taskService.getTaskByName(taskName), is(empty()));
    }

    private void assertTasksFoundByDateCreated(Date dateCrated, Task task){
        assertThat(taskService.getTaskByDateCreated(dateCrated), hasItem(task));
    }

    private void assertTasksNotFoundByDateCreated(Date dateCreated){
        assertThat(taskService.getTaskByDateCreated(dateCreated), is(empty()));
    }
}
