package pe.edu.upc.center.edunova.publishing.domain.model.commands;


import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.*;

import java.math.BigDecimal;

public record CreateCourseCommand(
        String name,
        String description,
        CourseCategory category,
        BigDecimal price,
        CreatorId creatorId,
        ImageUrl image,
        Language language,
        Difficulty difficulty
) {
}
