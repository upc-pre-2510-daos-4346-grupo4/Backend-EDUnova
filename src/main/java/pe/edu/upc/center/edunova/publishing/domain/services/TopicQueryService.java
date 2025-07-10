package pe.edu.upc.center.edunova.publishing.domain.services;



import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllTopicsQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetTopicByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TopicQueryService {
    List<Topic> handle(GetAllTopicsQuery query);
    Optional<Topic> handle(GetTopicByIdQuery query);
}
