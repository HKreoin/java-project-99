package hexlet.code.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Task implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Size(min = 1)
    @ToString.Include
    private String name;

    @ToString.Include
    private Long index;

    @Column(columnDefinition = "TEXT")
    @ToString.Include
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "task_status_id")
    @ToString.Include
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    @ToString.Include
    private User assignee;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;

    @ManyToMany
    private Set<Label> labels = new HashSet<>();

    public void addLabel(Label label) {
        labels.add(label);
        label.addTask(this);
    }

    public void removeLabel(Label label) {
        labels.remove(label);
        label.removeTask(this);
    }
}
