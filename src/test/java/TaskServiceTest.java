import com.company.tasks.models.Task;
import com.company.tasks.services.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TaskServiceTest{

    private final int ZERO = 0;

    @MockBean
    private TaskService taskService;

    @Test
    public void taskService_should_be_not_null(){
        assertNotNull(taskService);
    }

    @Test
    public void if_called_method_findAllTasks_then_allTasksList_size_more_or_equal_zero(){
        List<Task> allTasksList = taskService.findAllTasks();
        assertThat(allTasksList.size(), is(greaterThanOrEqualTo(ZERO)) );
    }

    @Test
    public void if_called_method_findAllTasks_then_allTasksList_not_equal_null(){
        List<Task> allTasksList = taskService.findAllTasks();
        assertNotNull(allTasksList);
    }

    @Test
    public void if_called_method_findAllCompletedTasks_then_completedTaskList_size_more_or_equal_zero(){
        List<Task> completedTasksList = taskService.findAllCompletedTasks();
        assertThat(completedTasksList.size(), is(greaterThanOrEqualTo(ZERO)) );
    }

    @Test
    public void if_called_method_findAllCompletedTasks_then_completedTaskList_not_equal_null(){
        List<Task> completedTaskList = taskService.findAllCompletedTasks();
        assertNotNull(completedTaskList);
    }

    @Test
    public void if_called_method_findAllTasksInWork_then_tasksInWorkList_size_more_or_equal_zero(){
        List<Task> tasksInWorkList = taskService.findAllTasksInWork();
        assertThat(tasksInWorkList.size(), is(greaterThanOrEqualTo(ZERO)) );
    }

    @Test
    public void if_called_method_findAllTasksInWork_then_tasksInWorkList_not_equal_null(){
        List<Task> tasksInWorkList = taskService.findAllTasksInWork();
        assertNotNull(tasksInWorkList);
    }

    @Test
    public void if_tasks_found_called_method_getTaskByName_then_return_list(){
        String taskName = "TaskName";
        Task task = new Task(       taskName,
                        "Description",
                                    new Date(01/01/2018));
        when(taskService.getTaskByName(taskName)).thenReturn(singletonList(task));

        assertTasksFoundByName(taskName, task);
    }

    @Test
    public void if_tasks_not_found_called_method_getTaskByName_then_return_empty_list(){
        String taskName = "someName";
        when(taskService.getTaskByName(taskName)).thenReturn(emptyList());

        assertTasksNotFoundByName(taskName);

        verify(taskService, only()).getTaskByName(taskName);
    }

    @Test
    public void if_tasks_found_called_method_getTaskByDateCreated_then_return_list(){
        Date dateCreated = new Date(01/01/2018);

        Task task = new Task(  "TaskName",
                           "Description",
                                      dateCreated
                            );
        when(taskService.getTaskByDateCreated(dateCreated)).thenReturn(singletonList(task));

        assertTasksFoundByDateCreated(dateCreated, task);
    }

    @Test
    public void if_tasks_not_found_called_method_getTaskByDateCreated_then_return_empty_list(){
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
