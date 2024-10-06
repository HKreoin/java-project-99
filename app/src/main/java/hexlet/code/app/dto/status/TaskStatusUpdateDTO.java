package hexlet.code.app.dto.status;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {

    @Size(min = 1)
    private JsonNullable<String> name;

    @Column(unique = true)
    @Size(min = 1)
    private JsonNullable<String> slug;
}
