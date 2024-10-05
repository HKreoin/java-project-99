package hexlet.code.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import hexlet.code.app.dto.status.TaskStatusCreateDTO;
import hexlet.code.app.dto.status.TaskStatusDTO;
import hexlet.code.app.dto.status.TaskStatusUpdateDTO;
import hexlet.code.app.model.TaskStatus;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskStatusMapper {

    public abstract TaskStatus map(TaskStatusCreateDTO data);

    public abstract TaskStatus map(TaskStatusUpdateDTO data);

    public abstract TaskStatusDTO map(TaskStatus model);

    public abstract TaskStatus map(TaskStatusDTO data);

    public abstract void update(TaskStatusUpdateDTO update, @MappingTarget TaskStatus target);

}
