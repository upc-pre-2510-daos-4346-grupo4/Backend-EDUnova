package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CourseId(Long courseId) {
    public CourseId() {
        this(null);
    }

    public CourseId {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("CourseId must be positive and not null");
        }
    }
}
