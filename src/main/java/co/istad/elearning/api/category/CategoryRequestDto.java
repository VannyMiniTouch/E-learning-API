package co.istad.elearning.api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
//        Integer id,
        @Size(min = 3, max = 100)
                @NotBlank
        String name,
        Boolean isDeleted
) {
}
