package co.istad.elearning.api.JpaSpecification;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecifications {

    public static Specification<Person> findDistinctByCountryCode(String countryCode) {
        return (root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.get("countryCode"), countryCode);
        };
    }
}