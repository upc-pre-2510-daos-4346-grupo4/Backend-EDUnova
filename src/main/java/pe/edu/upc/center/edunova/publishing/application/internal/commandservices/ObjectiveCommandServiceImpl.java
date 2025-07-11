package pe.edu.upc.center.edunova.publishing.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.services.CourseCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.ObjectiveCommandService;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.CourseRepository;
import pe.edu.upc.center.edunova.publishing.infrastructure.persistence.jpa.repositories.ObjectiveRepository;

import java.util.Optional;
@Service
public class ObjectiveCommandServiceImpl implements ObjectiveCommandService {
    private final ObjectiveRepository objectiveRepository;

    public ObjectiveCommandServiceImpl(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    @Override
    public Long handle(CreateObjectiveCommand command) {
        if (this.objectiveRepository.existsByTitleAndTopicId(command.title(), command.topicId())) {
            throw new IllegalArgumentException("Ya existe un objetivo con el título " + command.title() + " y el topicId proporcionado");
        }
        Objective objective = new Objective(command);
        try {
            this.objectiveRepository.save(objective);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el objetivo: " + e.getMessage());
        }
        return objective.getId();
    }


    @Override
    public Optional<Objective> handle(UpdateObjectiveCommand command) {
        if (this.objectiveRepository.existsByTitleAndTopicId(command.title(), command.topicId())) {
            Optional<Objective> existingObjective = objectiveRepository.findByTitleAndTopicId(command.title(), command.topicId());
            if (existingObjective.isPresent() && !existingObjective.get().getId().equals(command.objectiveId())) {
                throw new IllegalArgumentException("Ya existe un objetivo con el título " + command.title() + " y el topicId proporcionado");
            }
        }

        Optional<Objective> objectiveToUpdateOpt = this.objectiveRepository.findById(command.objectiveId());
        if (objectiveToUpdateOpt.isEmpty()) {
            throw new IllegalArgumentException("El objetivo con id " + command.objectiveId() + " no existe");
        }

        Objective objectiveToUpdate = objectiveToUpdateOpt.get();
        objectiveToUpdate.updateInformation(
                command.title(),
                command.header(),
                command.mainParagraph(),
                command.footer(),
                command.conclusion()
        );

        try {
            Objective updatedObjective = this.objectiveRepository.save(objectiveToUpdate);
            return Optional.of(updatedObjective);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el objetivo: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteObjectiveCommand command) {
        if (!this.objectiveRepository.existsById(command.objectiveId())) {
            throw new IllegalArgumentException("Objective with id " + command.objectiveId() + " does not exist");
        }

        try {
            this.objectiveRepository.deleteById(command.objectiveId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting objective: " + e.getMessage());
        }
    }
}
