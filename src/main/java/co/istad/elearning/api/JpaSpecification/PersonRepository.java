package co.istad.elearning.api.JpaSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findDistinctByCountryCode(String countryCode);

    List<Person> findDistinctByStatusAndAndCountryCode(Character status, String CountryCode);



}

