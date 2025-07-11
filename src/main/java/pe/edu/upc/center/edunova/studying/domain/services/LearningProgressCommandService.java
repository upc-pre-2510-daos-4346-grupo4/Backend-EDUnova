package pe.edu.upc.center.edunova.studying.domain.services;

import pe.edu.upc.center.edunova.studying.domain.model.commands.CreateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.domain.model.commands.UpdateLearningProgressCommand;
import java.util.Optional;

public interface LearningProgressCommandService {
    Long handle(CreateLearningProgressCommand command);
    Optional<?> handle(UpdateLearningProgressCommand command);
}