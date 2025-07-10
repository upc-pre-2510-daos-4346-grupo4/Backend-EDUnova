package pe.edu.upc.center.edunova.publishing.domain.model.commands;

public record UpdateObjectiveCommand(
        String title,
        String header,
        String mainParagraph,
        String footer,
        String conclusion
) {
}
