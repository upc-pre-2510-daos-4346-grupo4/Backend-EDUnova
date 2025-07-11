package pe.edu.upc.center.edunova.studying.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.center.edunova.studying.domain.model.queries.GetLearningProgressByUserIdQuery;
import pe.edu.upc.center.edunova.studying.domain.services.LearningProgressQueryService;
import pe.edu.upc.center.edunova.studying.infrastructure.persistence.jpa.repositories.LearningProgressRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningProgressQueryServiceImpl implements LearningProgressQueryService {

    private final LearningProgressRepository repository;

    @Override
    public Optional<?> handle(GetLearningProgressByUserIdQuery query) {
        return repository.findByUserId(query.getUserId());
    }
}