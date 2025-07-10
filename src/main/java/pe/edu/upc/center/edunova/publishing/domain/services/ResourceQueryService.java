package pe.edu.upc.center.edunova.publishing.domain.services;


import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllResourcesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetResourceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ResourceQueryService {
    List<Resource> handle(GetAllResourcesQuery query);
    Optional<Resource> handle(GetResourceByIdQuery query);
}
