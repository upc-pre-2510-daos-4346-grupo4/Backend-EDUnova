package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CreatorId(Long creatorId) {

    public CreatorId() {
        this(null);
    }

    public CreatorId {
        if (creatorId == null || creatorId <= 0) {
            throw new IllegalArgumentException("CreatorId must be positive and not null");
        }
    }
}
