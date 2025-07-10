package pe.edu.upc.center.edunova.publishing.domain.model.commands;

public record UpdateTopicCommand(
        String title,
        String description
) {
}
