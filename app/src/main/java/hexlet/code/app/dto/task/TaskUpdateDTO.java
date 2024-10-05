package hexlet.code.app.dto.task;

import hexlet.code.app.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateDTO {

    @Size(min = 1)
    private String name;

    private String index;

    private String description;

    @NotBlank
    private Long statusId;

    private User assigneeId;
}
