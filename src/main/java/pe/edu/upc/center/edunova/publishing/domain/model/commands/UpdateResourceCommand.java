package pe.edu.upc.center.edunova.publishing.domain.model.commands;

public record UpdateResourceCommand(
        String youtubeId,
        String title,
        String description
) {
}