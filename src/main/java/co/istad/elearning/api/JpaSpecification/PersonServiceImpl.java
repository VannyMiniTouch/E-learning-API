package co.istad.elearning.api.JpaSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    @Override
    public List<PersonDto> listDistinPerson(String countryCode) {
        Character status = 'U';
        List<Person> person = personRepository.findDistinctByStatusAndAndCountryCode(status, countryCode);
        List<PersonDto> personDtos = personMapper.toPersonDto(person);
        return  personDtos;
    }
}
