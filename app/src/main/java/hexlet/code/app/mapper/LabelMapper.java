package hexlet.code.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import hexlet.code.app.dto.label.LabelParamDTO;
import hexlet.code.app.dto.label.LabelDTO;
import hexlet.code.app.model.Label;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LabelMapper {

    public abstract Label map(LabelParamDTO data);

    public abstract LabelDTO map(Label model);

    public abstract Label map(LabelDTO data);

    public abstract void update(LabelParamDTO update, @MappingTarget Label target);

}
