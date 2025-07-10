package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseCategory;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.Difficulty;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.ImageUrl;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.Language;

import java.math.BigDecimal;

public record UpdateCourseCommand(
        String name,
        String description,
        CourseCategory category,
        BigDecimal price,
        ImageUrl image,
        Language language,
        Difficulty difficulty
) {
}