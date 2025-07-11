package pe.edu.upc.center.edunova.publishing.domain.model.queries;

public record GetAllCoursesQuery(Long creatorId) {
    public GetAllCoursesQuery() {
        this(null);
    }
}
