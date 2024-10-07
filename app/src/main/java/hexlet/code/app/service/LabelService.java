package hexlet.code.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import hexlet.code.app.dto.label.LabelDTO;
import hexlet.code.app.dto.label.LabelParamDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.LabelMapper;
import hexlet.code.app.repository.LabelRepository;

@Service
public class LabelService {
    @Autowired
    private LabelRepository repository;

    @Autowired
    private LabelMapper mapper;

    public List<LabelDTO> findAll() {
        var models = repository.findAll();
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public LabelDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Label with id: " + id + "not found"));
        return mapper.map(model);
    }

    public LabelDTO create(LabelParamDTO data) {
        var model = mapper.map(data);
        repository.save(model);
        return mapper.map(model);
    }

    public LabelDTO update(Long id, LabelParamDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Label with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
