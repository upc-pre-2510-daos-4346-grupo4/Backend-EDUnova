package pe.edu.upc.center.edunova.publishing.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteObjectiveCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllObjectivesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetObjectiveByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.ObjectiveCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.ObjectiveQueryService;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ObjectiveResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateObjectiveResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.ObjectiveResourceFromEntityAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.CreateObjectiveCommandFromResourceAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.UpdateObjectiveCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/objectives", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Objectives", description = "Objective Management Endpoints")
public class ObjectivesController {
    private final ObjectiveQueryService objectiveQueryService;
    private final ObjectiveCommandService objectiveCommandService;

    public ObjectivesController(ObjectiveQueryService objectiveQueryService, ObjectiveCommandService objectiveCommandService) {
        this.objectiveQueryService = objectiveQueryService;
        this.objectiveCommandService = objectiveCommandService;
    }

    @Operation(
            summary = "Obtener todos los objetivos",
            description = "Recupera una lista de todos los objetivos",
            operationId = "getAllObjectives",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de objetivos recuperada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ObjectiveResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ObjectiveResource>> getAllObjectives() {
        var query = new GetAllObjectivesQuery();
        var objectives = objectiveQueryService.handle(query);
        var resources = objectives.stream()
                .map(ObjectiveResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Obtener un objetivo por ID",
            description = "Recupera los detalles de un objetivo por su ID",
            operationId = "getObjectiveById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Objetivo encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ObjectiveResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Objetivo no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{objectiveId}")
    public ResponseEntity<ObjectiveResource> getObjectiveById(@PathVariable Long objectiveId) {
        var query = new GetObjectiveByIdQuery(objectiveId);
        var optionalObjective = objectiveQueryService.handle(query);

        if (optionalObjective.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var resource = ObjectiveResourceFromEntityAssembler.toResourceFromEntity(optionalObjective.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(
            summary = "Crear un nuevo objetivo",
            description = "Registrar un nuevo objetivo en el sistema",
            operationId = "createObjective",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Objetivo creado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ObjectiveResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ObjectiveResource> createObjective(@RequestBody CreateObjectiveResource resource) {
        var createCommand = CreateObjectiveCommandFromResourceAssembler.toCommandFromResource(resource);
        var objectiveId = objectiveCommandService.handle(createCommand);

        if (objectiveId == null || objectiveId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getObjectiveByIdQuery = new GetObjectiveByIdQuery(objectiveId);
        var optionalObjective = objectiveQueryService.handle(getObjectiveByIdQuery);

        if (optionalObjective.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var objectiveResource = ObjectiveResourceFromEntityAssembler.toResourceFromEntity(optionalObjective.get());
        return new ResponseEntity<>(objectiveResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un objetivo",
            description = "Actualiza los detalles de un objetivo",
            operationId = "updateObjective",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Objetivo actualizado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ObjectiveResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{objectiveId}")
    public ResponseEntity<ObjectiveResource> updateObjective(@PathVariable Long objectiveId, @RequestBody ObjectiveResource resource) {
        var updateCommand = UpdateObjectiveCommandFromResourceAssembler.toCommandFromResource(objectiveId, resource);
        var optionalObjective = objectiveCommandService.handle(updateCommand);

        if (optionalObjective.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var updatedResource = ObjectiveResourceFromEntityAssembler.toResourceFromEntity(optionalObjective.get());
        return ResponseEntity.ok(updatedResource);
    }

    @Operation(
            summary = "Eliminar un objetivo",
            description = "Elimina un objetivo del sistema",
            operationId = "deleteObjective",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Objetivo eliminado exitosamente"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{objectiveId}")
    public ResponseEntity<?> deleteObjective(@PathVariable Long objectiveId) {
        var deleteCommand = new DeleteObjectiveCommand(objectiveId);
        objectiveCommandService.handle(deleteCommand);
        return ResponseEntity.noContent().build();
    }
}