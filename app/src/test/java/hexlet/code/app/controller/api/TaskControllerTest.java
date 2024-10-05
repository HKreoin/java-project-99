package hexlet.code.app.controller.api;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexlet.code.app.dto.task.TaskDTO;
import hexlet.code.app.mapper.TaskMapper;
import hexlet.code.app.model.Task;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.util.ModelGenerator;
import hexlet.code.app.util.UserUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private ModelGenerator modelGenerator;
    
    @Autowired
    private  UserUtils userUtils;

    private JwtRequestPostProcessor token;

    private Task testTask;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .apply(springSecurity())
                .build();

        token = jwt().jwt(builder -> builder.subject("hexlet@example.com"));
        var currentUser = userUtils.getCurrentUser();
        var status = Instancio.of(modelGenerator.getTaskStatusModel())
                .create();
        testTask = Instancio.of(modelGenerator.getTaskModel())
                .create();
        currentUser.addTask(testTask);
        status.addTask(testTask);
    }

    @Test
    public void testIndex() throws Exception {
        taskRepository.save(testTask);
        var response = mockMvc.perform(get("/api/tasks")
                .with(token))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = response.getContentAsString();

        List<TaskDTO> taskDTOS = objectMapper.readValue(body, new TypeReference<>() { });

        var actual = taskDTOS.stream().map(taskMapper::map).toList();
        var expected = taskRepository.findAll();

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testShow() throws Exception {
        taskRepository.save(testTask);
        var request = get("/api/tasks/" + testTask.getId())
                .with(token);
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = result.getContentAsString();
        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(testTask.getName()),
                v -> v.node("index").isEqualTo(testTask.getIndex()));
    }

    @Test
    public void testCreate() throws Exception {
        var request = post("/api/tasks")
                .with(token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testTask));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var task =  taskRepository.findByName(testTask.getName()).get();

        assertNotNull(task);
        assertEquals(task.getIndex(), testTask.getIndex());
        assertNotNull(task.getCreatedAt());
    }

    @Test
    public void testUpdate() throws Exception {
        var id = testTask.getId();
        var name = testTask.getName();
        var testDescription = "Test description lupus lamus obre tamus";

        var data = new HashMap<>();
        data.put("description", testDescription);

        var request = put("/api/tasks/" + id)
                .with(token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var task = taskRepository.findById(id).get();

        assertThat(task.getName()).isEqualTo(name);
        assertThat(task.getDescription()).isEqualTo(testDescription);
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testDelete() throws Exception {
        var id = testTask.getId();
        var request = delete("/api/tasks/" + id).with(token);
        mockMvc.perform(request);
        assertThat(taskRepository.findById(id).isEmpty()).isTrue();
    }

}
