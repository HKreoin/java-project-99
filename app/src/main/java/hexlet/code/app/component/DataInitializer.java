package hexlet.code.app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.mapper.UserMapper;
import hexlet.code.app.repository.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        var userData = new UserCreateDTO();
        var email = "hexlet@example.com";
        userData.setEmail(email);
        userData.setPassword("qwerty");
        var user = mapper.map(userData);
        userRepository.save(user);
    }
}
