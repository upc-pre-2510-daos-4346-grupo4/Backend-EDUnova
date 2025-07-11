package pe.edu.upc.center.edunova.publishing.application.internal.queryservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllResourcesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetResourceByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.ResourceQueryService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ResourceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceQueryServiceImpl implements ResourceQueryService {
    private final ResourceRepository resourceRepository;

    public ResourceQueryServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Optional<Resource> handle(GetResourceByIdQuery query) {
        return resourceRepository.findById(query.resourceId());
    }

    @Override
    public List<Resource> handle(GetAllResourcesQuery query) {
        return resourceRepository.findAll();
    }
}
