package pe.edu.upc.center.edunova.publishing.interfaces.rest.resources;

public record CreateObjectiveResource(
        Long topicId,
        String title,
        String header,
        String mainParagraph,
        String footer,
        String conclusion
) {
    public CreateObjectiveResource {
        if (topicId == null || topicId <= 0) {
            throw new IllegalArgumentException("El topicId es obligatorio y debe ser positivo");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (header == null || header.isBlank()) {
            throw new IllegalArgumentException("El encabezado es obligatorio");
        }
        if (mainParagraph == null || mainParagraph.isBlank()) {
            throw new IllegalArgumentException("El párrafo principal es obligatorio");
        }
        if (footer == null || footer.isBlank()) {
            throw new IllegalArgumentException("El pie de página es obligatorio");
        }
        if (conclusion == null || conclusion.isBlank()) {
            throw new IllegalArgumentException("La conclusión es obligatoria");
        }
    }
}
