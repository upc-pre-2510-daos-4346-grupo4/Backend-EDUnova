package pe.edu.upc.center.edunova.publishing.domain.services;


import pe.edu.upc.center.edunova.publishing.domain.model.aggregates.Resource;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.UpdateResourceCommand;

import java.util.Optional;

public interface ResourceCommandService {
    Long handle(CreateResourceCommand command);
    Optional<Resource> handle(UpdateResourceCommand command);
    void handle(DeleteResourceCommand command);
}
