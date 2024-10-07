package hexlet.code.app.dto.label;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelUpsertDTO {
    @Size(min = 3, max = 1000)
    @Column(columnDefinition = "TEXT")
    private String name;
}
