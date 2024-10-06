package hexlet.code.app.component;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import hexlet.code.app.dto.status.TaskStatusCreateDTO;
import hexlet.code.app.dto.task.TaskCreateDTO;
import hexlet.code.app.dto.user.UserCreateDTO;
import hexlet.code.app.service.TaskService;
import hexlet.code.app.service.TaskStatusService;
import hexlet.code.app.service.UserService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {


    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var userData = new UserCreateDTO();
        var email = "hexlet@example.com";
        userData.setEmail(email);
        userData.setPassword("qwerty");
        userService.create(userData);

        var taskStatuses = Map.of(
                "Draft", "draft",
                "ToReview", "to_review",
                "ToBeFixed", "to_be_fixed",
                "ToPublish", "to_publish",
                "Published", "published");

        taskStatuses.forEach((k, v) -> {
            var statusData = new TaskStatusCreateDTO();
            statusData.setName(k);
            statusData.setSlug(v);
            taskStatusService.create(statusData);
        });

        var task = new TaskCreateDTO();
        task.setTitle("Task 1");
        task.setContent("Description 1");
        task.setStatus("draft");
        task.setAssigneeId(1L);
        taskService.create(task);
    }
}
