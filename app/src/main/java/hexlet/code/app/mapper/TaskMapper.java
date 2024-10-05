package hexlet.code.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import hexlet.code.app.dto.task.TaskCreateDTO;
import hexlet.code.app.dto.task.TaskDTO;
import hexlet.code.app.dto.task.TaskUpdateDTO;
import hexlet.code.app.model.Task;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "status", source = "statusId")
    public abstract Task map(TaskCreateDTO data);

    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "status", source = "statusId")
    public abstract Task map(TaskUpdateDTO data);

    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "statusId", source = "status.id")
    public abstract TaskDTO map(Task model);

    @Mapping(target = "assignee.id", source = "assigneeId")
    @Mapping(target = "status.id", source = "statusId")
    public abstract Task map(TaskDTO data);

    public abstract void update(TaskUpdateDTO update, @MappingTarget Task target);

}
