package hexlet.code.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import hexlet.code.dto.label.LabelUpsertDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.model.Label;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LabelMapper {

    public abstract Label map(LabelUpsertDTO data);

    public abstract LabelDTO map(Label model);

    public abstract Label map(LabelDTO data);

    public abstract void update(LabelUpsertDTO update, @MappingTarget Label target);

}
