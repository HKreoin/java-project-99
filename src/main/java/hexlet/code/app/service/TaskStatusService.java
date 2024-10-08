package hexlet.code.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import hexlet.code.app.dto.status.TaskStatusCreateDTO;
import hexlet.code.app.dto.status.TaskStatusDTO;
import hexlet.code.app.dto.status.TaskStatusUpdateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.repository.TaskStatusRepository;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusRepository repository;

    @Autowired
    private TaskStatusMapper mapper;

    public List<TaskStatusDTO> findAll() {
        var models = repository.findAll();
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public TaskStatusDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Task status with id: " + id + "not found"));
        return mapper.map(model);
    }

    public TaskStatusDTO create(TaskStatusCreateDTO data) {
        var model = mapper.map(data);
        repository.save(model);
        return mapper.map(model);
    }

    public TaskStatusDTO update(Long id, TaskStatusUpdateDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Task status with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
