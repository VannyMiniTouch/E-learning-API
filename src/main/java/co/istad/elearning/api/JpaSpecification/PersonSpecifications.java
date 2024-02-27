package co.istad.elearning.api.JpaSpecification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecifications {

//    public static Specification<Person> findDistinctByCountryCode(String countryCode) {
//        return (root, query, builder) -> {
//            query.distinct(true);
//            return builder.equal(root.get("countryCode"), countryCode);
//        };
//    }


    public static Specification<Person> hasCountryCode(String countryCode) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            query.multiselect(root.get("id"), root.get("name"), root.get("status"));
            Predicate predicate = criteriaBuilder.equal(root.get("countryCode"), countryCode);
            return predicate;
        };
    }
}