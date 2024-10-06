package hexlet.code.app.dto.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateDTO {

    @Size(min = 1)
    private String title;

    private Long index;

    private String content;

    @NotNull
    private String status;

    private Long assigneeId;
}
