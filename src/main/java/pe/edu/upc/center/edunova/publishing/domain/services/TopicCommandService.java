package pe.edu.upc.center.edunova.publishing.domain.services;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateTopicCommand;

import java.util.Optional;

public interface TopicCommandService {
    Long handle(CreateTopicCommand command);
    Optional<Topic> handle(UpdateTopicCommand command);
    void handle(DeleteTopicCommand command);
}
