package pe.edu.upc.center.edunova.publishing.domain.services;

import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Objective;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateObjectiveCommand;

import java.util.Optional;

public interface ObjectiveCommandService {
    Long handle(CreateObjectiveCommand command);
    Optional<Objective> handle(UpdateObjectiveCommand command);
    void handle(DeleteObjectiveCommand command);
}
