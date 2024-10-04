package hexlet.code.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "task_statuses")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class TaskStatus implements BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank
    @Size(min = 1)
    @ToString.Include
    private String name;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1)
    @ToString.Include
    private String slug;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;
}
