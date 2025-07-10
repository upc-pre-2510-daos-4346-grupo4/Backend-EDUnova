package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

public record CreateResourceCommand(
        TopicId topicId,
        String youtubeId,
        String title,
        String description
) {
}