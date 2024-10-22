package org.demo.app.mapper;

import org.demo.app.dto.EmployeeDto;
import org.demo.app.model.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends BaseMapper<EmployeeEntity, EmployeeDto> {
}
