package co.istad.elearning.api.instructor;

import co.istad.elearning.api.instructor.dto.InstructorCreationDto;
import co.istad.elearning.api.instructor.dto.InstructorDto;
import co.istad.elearning.api.instructor.dto.InstructorEditionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    // 1. Source Type
    // 2. Target Type
    List<InstructorDto> toInstructorListDto(List<Instructor> instructors);

    InstructorDto toInstructorDto(Instructor instructor);

    Instructor fromInstructorCreationDto(InstructorCreationDto instructorCreationDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromInstructorEditionDto(@MappingTarget Instructor instructor, InstructorEditionDto instructorEditionDto);
}
