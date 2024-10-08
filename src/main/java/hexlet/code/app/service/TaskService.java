package hexlet.code.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import hexlet.code.app.dto.task.TaskCreateDTO;
import hexlet.code.app.dto.task.TaskDTO;
import hexlet.code.app.dto.task.TaskParamsDTO;
import hexlet.code.app.dto.task.TaskUpdateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.TaskMapper;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.specification.TaskSpecification;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private TaskSpecification specification;

    public List<TaskDTO> findAll(TaskParamsDTO params) {
        var spec = specification.build(params);
        var models = repository.findAll(spec);
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public TaskDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Task with id: " + id + "not found"));
        return mapper.map(model);
    }

    public TaskDTO create(TaskCreateDTO data) {
        var index = (long) (Math.random() * 9999);
        while (repository.findByIndex(index).isPresent()) {
            index = (long) (Math.random() * 9999);
        }
        var model = mapper.map(data);
        model.setIndex(index);
        repository.save(model);
        return mapper.map(model);
    }

    public TaskDTO update(Long id, TaskUpdateDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Task with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
