package pe.edu.upc.center.edunova.publishing.application.internal.commandservices;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Topic;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.services.ResourceCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.TopicCommandService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ResourceRepository;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.TopicRepository;

import java.util.Optional;

public class TopicCommandServiceImpl implements TopicCommandService {
    private final TopicRepository topicRepository;

    public TopicCommandServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void handle(DeleteTopicCommand command) {
        if (!this.topicRepository.existsById(command.topicId())) {
            throw new IllegalArgumentException("El topic con id " + command.topicId() + " no existe");
        }
        try {
            this.topicRepository.deleteById(command.topicId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el topic: " + e.getMessage());
        }
    }

    @Override
    public Optional<Topic> handle(UpdateTopicCommand command) {
        if (this.topicRepository.existsByTitleAndCourseId(command.title(), command.courseId())) {
            Optional<Topic> existingTopic = topicRepository.findByTitleAndCourseId(command.title(), command.courseId());
            if (existingTopic.isPresent() && !existingTopic.get().getId().equals(command.topicId())) {
                throw new IllegalArgumentException("Ya existe un topic con el título " + command.title() + " y el courseId proporcionado");
            }
        }

        Optional<Topic> topicToUpdateOpt = this.topicRepository.findById(command.topicId());
        if (topicToUpdateOpt.isEmpty()) {
            throw new IllegalArgumentException("El topic con id " + command.topicId() + " no existe");
        }

        Topic topicToUpdate = topicToUpdateOpt.get();
        topicToUpdate.update(command.title(), command.description());

        try {
            Topic updatedTopic = this.topicRepository.save(topicToUpdate);
            return Optional.of(updatedTopic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el topic: " + e.getMessage());
        }
    }

    @Override
    public Long handle(CreateTopicCommand command) {
        if (this.topicRepository.existsByTitleAndCourseId(command.title(), command.courseId())) {
            throw new IllegalArgumentException("Ya existe un topic con el título " + command.title() + " y el courseId proporcionado");
        }
        Topic topic = new Topic(command);
        try {
            this.topicRepository.save(topic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el topic: " + e.getMessage());
        }
        return topic.getId();
    }
}
