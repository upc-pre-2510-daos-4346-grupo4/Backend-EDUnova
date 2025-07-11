package pe.edu.upc.center.edunova.studying.domain.services;

import pe.edu.upc.center.edunova.studying.domain.model.queries.GetLearningProgressByUserIdQuery;
import java.util.Optional;

public interface LearningProgressQueryService {
    Optional<?> handle(GetLearningProgressByUserIdQuery query);
}