package hexlet.code.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskParamsDTO {

    private String titleCont;

    private String status;

    private Long assigneeId;

    private Long labelId;
}
