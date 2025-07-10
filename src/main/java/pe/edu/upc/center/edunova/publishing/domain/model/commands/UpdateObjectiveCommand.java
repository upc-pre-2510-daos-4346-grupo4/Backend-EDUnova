package pe.edu.upc.center.edunova.publishing.domain.model.commands;

import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;

public record UpdateObjectiveCommand(
        Long objectiveId,
        TopicId topicId,
        String title,
        String header,
        String mainParagraph,
        String footer,
        String conclusion
) {
}
