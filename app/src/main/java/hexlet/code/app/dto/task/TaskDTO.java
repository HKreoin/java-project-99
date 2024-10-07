package hexlet.code.app.dto.task;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private String title;

    private Long index;

    private String content;

    private String status;

    @JsonProperty("assignee_id")
    private Long assigneeId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    private Set<Long> taskLabelIds = new HashSet<>();
}
