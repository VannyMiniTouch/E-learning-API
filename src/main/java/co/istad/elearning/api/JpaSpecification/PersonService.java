package co.istad.elearning.api.JpaSpecification;

import java.util.List;

public interface PersonService {
    List<PersonDto> listDistinPerson(String countryCode);
    List<Person> SpecificatoinPS();
}
