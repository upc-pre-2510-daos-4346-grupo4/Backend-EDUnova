package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

public record UpdateResourceCommand(
        Long resourceId,
        TopicId topicId,
        String youtubeId,
        String title,
        String description
) {
}