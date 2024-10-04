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

import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.dto.UserDTO;
import hexlet.code.app.dto.UserUpdateDTO;
import hexlet.code.app.mapper.UserMapper;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDTO>> index() {
        var users = userService.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(users.size()))
                .body(users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO show(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO create(@Valid @RequestBody UserCreateDTO user) {
        return userService.create(user);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO user) {
        return userService.update(id, user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        userService.delete(id);
    }

}
