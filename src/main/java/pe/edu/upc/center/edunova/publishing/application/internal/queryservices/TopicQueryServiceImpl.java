package pe.edu.upc.center.edunova.publishing.application.internal.queryservices;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllTopicsQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetTopicByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.TopicQueryService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

public class TopicQueryServiceImpl implements TopicQueryService {
    private final TopicRepository topicRepository;

    public TopicQueryServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<Topic> handle(GetTopicByIdQuery query) {
        return topicRepository.findById(query.topicId());
    }

    @Override
    public List<Topic> handle(GetAllTopicsQuery query) {
        return topicRepository.findAll();
    }
}
