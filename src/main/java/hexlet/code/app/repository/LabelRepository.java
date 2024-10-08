package hexlet.code.app.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hexlet.code.app.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);

    @Query("SELECT l FROM Label l WHERE l.id IN :labelsIds")
    Set<Label> findByIdIn(@Param("labelsIds") Set<Long> labelsIds);
}
