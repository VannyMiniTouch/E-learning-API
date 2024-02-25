package co.istad.elearning.api.JpaSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/country-code/{countryCode}")
    public List<PersonDto> listDistinPerson(@PathVariable String countryCode){
        return  personService.listDistinPerson(countryCode);
    }
}
