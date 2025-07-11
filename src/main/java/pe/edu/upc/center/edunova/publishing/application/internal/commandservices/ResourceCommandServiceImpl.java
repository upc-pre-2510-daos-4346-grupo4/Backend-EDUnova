package pe.edu.upc.center.edunova.publishing.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.services.ObjectiveCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.ResourceCommandService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ObjectiveRepository;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ResourceRepository;

import java.util.Optional;
@Service
public class ResourceCommandServiceImpl implements ResourceCommandService {
    private final ResourceRepository resourceRepository;

    public ResourceCommandServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void handle(DeleteResourceCommand command) {
        if (!this.resourceRepository.existsById(command.resourceId())) {
            throw new IllegalArgumentException("El recurso con id " + command.resourceId() + " no existe");
        }
        try {
            this.resourceRepository.deleteById(command.resourceId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el recurso: " + e.getMessage());
        }
    }

    @Override
    public Optional<Resource> handle(UpdateResourceCommand command) {
        if (this.resourceRepository.existsByTopicIdAndYoutubeIdAndTitle(
                command.topicId(), command.youtubeId(), command.title())) {
            Optional<Resource> existingResource = resourceRepository.findByTopicIdAndYoutubeIdAndTitle(
                    command.topicId(), command.youtubeId(), command.title());
            if (existingResource.isPresent() && !existingResource.get().getId().equals(command.resourceId())) {
                throw new IllegalArgumentException("Ya existe un recurso con el topicId, youtubeId y título proporcionados");
            }
        }

        Optional<Resource> resourceToUpdateOpt = this.resourceRepository.findById(command.resourceId());
        if (resourceToUpdateOpt.isEmpty()) {
            throw new IllegalArgumentException("El recurso con id " + command.resourceId() + " no existe");
        }

        Resource resourceToUpdate = resourceToUpdateOpt.get();
        resourceToUpdate.update(
                command.youtubeId(),
                command.title(),
                command.description()
        );

        try {
            Resource updatedResource = this.resourceRepository.save(resourceToUpdate);
            return Optional.of(updatedResource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el recurso: " + e.getMessage());
        }
    }

    @Override
    public Long handle(CreateResourceCommand command) {
        if (this.resourceRepository.existsByTopicIdAndYoutubeIdAndTitle(
                command.topicId(), command.youtubeId(), command.title())) {
            throw new IllegalArgumentException("Ya existe un recurso con el topicId, youtubeId y título proporcionados");
        }
        Resource resource = new Resource(command);
        try {
            this.resourceRepository.save(resource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el recurso: " + e.getMessage());
        }
        return resource.getId();
    }
}
