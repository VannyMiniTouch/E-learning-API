package co.istad.elearning.api.instructor;

import java.util.List;

public interface InstructorService {

    List<InstructorDto> search(
            String familyName,
            String givenName,
            String biography);

    InstructorDto findById(Integer id);

    void deleteById(Integer id);

    InstructorDto editById(Integer id, InstructorEditionDto instructorEditionDto);

    void createNew(InstructorCreationDto instructorCreationDto);

    InstructorDto findByIdAndNationalIdCard(Integer id, String nationalIdCard);

    List<InstructorDto> findList(String q);

}
