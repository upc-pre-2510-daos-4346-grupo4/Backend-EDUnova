package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

public record CreateObjectiveCommand(
        TopicId topicId,
        String title,
        String header,
        String mainParagraph,
        String footer,
        String conclusion
) {
}
