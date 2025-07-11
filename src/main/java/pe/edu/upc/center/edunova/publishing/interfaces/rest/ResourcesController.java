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
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteResourceCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllResourcesQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetResourceByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.ResourceCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.ResourceQueryService;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.ResourceResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateResourceResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.ResourceResourceFromEntityAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.CreateResourceCommandFromResourceAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.UpdateResourceCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/resources", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Resources", description = "Resource Management Endpoints")
public class ResourcesController {
    private final ResourceQueryService resourceQueryService;
    private final ResourceCommandService resourceCommandService;

    public ResourcesController(ResourceQueryService resourceQueryService, ResourceCommandService resourceCommandService) {
        this.resourceQueryService = resourceQueryService;
        this.resourceCommandService = resourceCommandService;
    }

    @Operation(
            summary = "Obtener todos los recursos",
            description = "Recupera una lista de todos los recursos",
            operationId = "getAllResources",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de recursos recuperada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ResourceResource>> getAllResources() {
        var query = new GetAllResourcesQuery();
        var resources = resourceQueryService.handle(query);
        var resourceList = resources.stream()
                .map(ResourceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resourceList);
    }

    @Operation(
            summary = "Obtener un recurso por ID",
            description = "Recupera los detalles de un recurso por su ID",
            operationId = "getResourceById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> getResourceById(@PathVariable Long resourceId) {
        var query = new GetResourceByIdQuery(resourceId);
        var optionalResource = resourceQueryService.handle(query);

        if (optionalResource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var resource = ResourceResourceFromEntityAssembler.toResourceFromEntity(optionalResource.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(
            summary = "Crear un nuevo recurso",
            description = "Registrar un nuevo recurso en el sistema",
            operationId = "createResource",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso creado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceResource.class)
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
    public ResponseEntity<ResourceResource> createResource(@RequestBody CreateResourceResource resource) {
        var createCommand = CreateResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        var resourceId = resourceCommandService.handle(createCommand);

        if (resourceId == null || resourceId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var optionalResource = resourceQueryService.handle(getResourceByIdQuery);

        if (optionalResource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(optionalResource.get());
        return new ResponseEntity<>(resourceResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un recurso",
            description = "Actualiza los detalles de un recurso",
            operationId = "updateResource",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso actualizado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> updateResource(@PathVariable Long resourceId, @RequestBody ResourceResource resource) {
        var updateCommand = UpdateResourceCommandFromResourceAssembler.toCommandFromResource(resourceId, resource);
        var optionalResource = resourceCommandService.handle(updateCommand);

        if (optionalResource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var updatedResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(optionalResource.get());
        return ResponseEntity.ok(updatedResource);
    }

    @Operation(
            summary = "Eliminar un recurso",
            description = "Elimina un recurso del sistema",
            operationId = "deleteResource",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Recurso eliminado exitosamente"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable Long resourceId) {
        var deleteCommand = new DeleteResourceCommand(resourceId);
        resourceCommandService.handle(deleteCommand);
        return ResponseEntity.noContent().build();
    }
}
