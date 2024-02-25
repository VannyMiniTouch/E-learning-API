package co.istad.elearning.api.category.dto;

import lombok.Builder;
import lombok.Getter;

//@Getter
@Builder
public record CategoryDto(
        Integer id,
        String name
) {
}
