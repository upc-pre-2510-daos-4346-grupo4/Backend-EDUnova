package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TopicId(Long topicId) {
    public TopicId() {
        this(null);
    }

    public TopicId {
        if (topicId == null || topicId <= 0) {
            throw new IllegalArgumentException("TopicId must be positive and not null");
        }
    }
}
