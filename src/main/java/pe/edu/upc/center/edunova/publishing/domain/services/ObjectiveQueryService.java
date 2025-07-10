package pe.edu.upc.center.edunova.publishing.domain.services;


import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllObjectivesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetObjectiveByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ObjectiveQueryService {
    List<Objective> handle(GetAllObjectivesQuery query);
    Optional<Objective> handle(GetObjectiveByIdQuery query);
}
