package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record ObjectiveResource(
        Long id,
        Long topicId,
        String title,
        String header,
        String mainParagraph,
        String footer,
        String conclusion
) {
}
