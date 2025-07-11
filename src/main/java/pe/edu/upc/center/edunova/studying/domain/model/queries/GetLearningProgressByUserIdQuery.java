package pe.edu.upc.center.edunova.studying.domain.model.queries;

import lombok.Getter;

@Getter
public class GetLearningProgressByUserIdQuery {
    private final Long userId;
    public GetLearningProgressByUserIdQuery(Long userId){ this.userId = userId; }
}