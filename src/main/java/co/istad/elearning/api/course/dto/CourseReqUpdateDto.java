package co.istad.elearning.api.course.dto;

import lombok.Builder;

@Builder
public record CourseReqUpdateDto(
        String title,
        String thumbnail,
        String description,
        Boolean isFree,
        Boolean isDeleted) {
}
