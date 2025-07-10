package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;

public record CreateTopicCommand(
        String title,
        String description,
        CourseId courseId
) {
}