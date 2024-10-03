package hexlet.code.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.dto.UserDTO;
import hexlet.code.app.dto.UserUpdateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.UserMapper;
import hexlet.code.app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserDTO> findAll() {
        var users = repository.findAll();
        var result = users.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public UserDTO findById(Long id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with id: " + id + "not found"));
        return mapper.map(user);
    }

    public UserDTO create(UserCreateDTO data) {
        var user = mapper.map(data);
        repository.save(user);
        return mapper.map(user);
    }

    public UserDTO update(Long id, UserUpdateDTO data) {
        var user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with id: " + id + "not found"));
        mapper.update(data, user);
        repository.save(user);
        return mapper.map(user);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
