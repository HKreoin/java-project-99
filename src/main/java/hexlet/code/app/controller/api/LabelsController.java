package hexlet.code.app.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hexlet.code.app.dto.label.LabelDTO;
import hexlet.code.app.dto.label.LabelUpsertDTO;
import hexlet.code.app.service.LabelService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/labels")
public class LabelsController {

    @Autowired
    private LabelService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<LabelDTO>> index() {
        var dtos = service.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(dtos.size()))
                .body(dtos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LabelDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    LabelDTO create(@Valid @RequestBody LabelUpsertDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    LabelDTO update(@PathVariable Long id, @Valid @RequestBody LabelUpsertDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        service.delete(id);
    }

}
