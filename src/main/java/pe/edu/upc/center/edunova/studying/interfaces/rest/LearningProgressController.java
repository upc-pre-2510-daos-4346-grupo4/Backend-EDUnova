package pe.edu.upc.center.edunova.studying.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.edunova.studying.domain.model.commands.CreateLearningProgressCommand;
import pe.edu.upc.center.edunova.studying.domain.model.queries.GetLearningProgressByUserIdQuery;
import pe.edu.upc.center.edunova.studying.domain.services.LearningProgressCommandService;
import pe.edu.upc.center.edunova.studying.domain.services.LearningProgressQueryService;
import pe.edu.upc.center.edunova.studying.interfaces.rest.resources.LearningProgressResource;
import pe.edu.upc.center.edunova.studying.interfaces.rest.resources.UpdateLearningProgressResource;
import pe.edu.upc.center.edunova.studying.interfaces.rest.transform.LearningProgressResourceFromEntityAssembler;
import pe.edu.upc.center.edunova.studying.interfaces.rest.transform.UpdateLearningProgressCommandFromResourceAssembler;

@RestController
@RequestMapping("/api/v1/learning-progress")
@Tag(name = "Learning Progress", description = "Endpoints for students' progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LearningProgressController {

    private final LearningProgressQueryService queryService;
    private final LearningProgressCommandService commandService;

    @Operation(summary = "Obtener progreso por userId")
    @GetMapping
    public ResponseEntity<LearningProgressResource> getByUserId(@RequestParam Long userId) {
        var query = new GetLearningProgressByUserIdQuery(userId);
        var optional = queryService.handle(query);
        return optional.map(entity ->
                        ResponseEntity.ok(LearningProgressResourceFromEntityAssembler.toResourceFromEntity((pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress) entity)))
                .orElseGet(() -> {
                    // Si no existe, lo creamos vacío para ese usuario
                    var newId = commandService.handle(new CreateLearningProgressCommand(userId));
                    var newEntity = queryService.handle(new GetLearningProgressByUserIdQuery(userId)).get();
                    return ResponseEntity.ok(
                            LearningProgressResourceFromEntityAssembler.toResourceFromEntity(
                                    (pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress) newEntity));
                });
    }

    @Operation(summary = "Actualizar progreso")
    @PutMapping("/{id}")
    public ResponseEntity<LearningProgressResource> updateProgress(
            @PathVariable Long id,
            @RequestBody UpdateLearningProgressResource resource) {

        var command = UpdateLearningProgressCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optional = commandService.handle(command);

        return optional.<ResponseEntity<LearningProgressResource>>map(entity ->
                        ResponseEntity.ok(LearningProgressResourceFromEntityAssembler.toResourceFromEntity(
                                (pe.edu.upc.center.edunova.studying.domain.model.aggregates.LearningProgress) entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}