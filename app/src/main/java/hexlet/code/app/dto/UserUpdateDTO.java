package hexlet.code.app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @NotBlank
    @Size(min = 3)
    private String password;
}
