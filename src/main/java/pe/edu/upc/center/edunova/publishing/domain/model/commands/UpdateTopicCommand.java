package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;

public record UpdateTopicCommand(
        Long topicId,
        String title,
        String description,
        CourseId courseId
) {
}
