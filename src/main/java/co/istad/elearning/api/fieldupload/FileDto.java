package co.istad.elearning.api.fieldupload;

import lombok.Builder;

@Builder
public record FileDto(
        String name,
        String extension,
        Long size,
        String uri
) {
}
