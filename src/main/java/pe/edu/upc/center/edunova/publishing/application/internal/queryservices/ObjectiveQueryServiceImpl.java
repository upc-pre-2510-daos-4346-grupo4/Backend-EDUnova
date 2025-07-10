package pe.edu.upc.center.edunova.publishing.application.internal.queryservices;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllObjectivesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetObjectiveByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.ObjectiveQueryService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ObjectiveRepository;

import java.util.List;
import java.util.Optional;

public class ObjectiveQueryServiceImpl implements ObjectiveQueryService {
    private final ObjectiveRepository objectiveRepository;

    public ObjectiveQueryServiceImpl(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    @Override
    public Optional<Objective> handle(GetObjectiveByIdQuery query) {
        return objectiveRepository.findById(query.objectiveId());
    }

    @Override
    public List<Objective> handle(GetAllObjectivesQuery query) {
        return objectiveRepository.findAll();
    }
}
