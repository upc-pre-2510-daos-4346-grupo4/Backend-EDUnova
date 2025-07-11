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
import pe.edu.upc.center.edunova.publishing.domain.model.commands.DeleteTopicCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetAllTopicsQuery;
import pe.edu.upc.center.edunova.publishing.domain.model.queries.GetTopicByIdQuery;
import pe.edu.upc.center.edunova.publishing.domain.services.TopicCommandService;
import pe.edu.upc.center.edunova.publishing.domain.services.TopicQueryService;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.TopicResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.resources.CreateTopicResource;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.TopicResourceFromEntityAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.CreateTopicCommandFromResourceAssembler;
import pe.edu.upc.center.edunova.publishing.interfaces.rest.transform.UpdateTopicCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/topics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Topics", description = "Topic Management Endpoints")
public class TopicsController {
    private final TopicQueryService topicQueryService;
    private final TopicCommandService topicCommandService;

    public TopicsController(TopicQueryService topicQueryService, TopicCommandService topicCommandService) {
        this.topicQueryService = topicQueryService;
        this.topicCommandService = topicCommandService;
    }

    @Operation(
            summary = "Obtener todos los temas",
            description = "Recupera una lista de todos los temas",
            operationId = "getAllTopics",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de temas recuperada exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicResource.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<TopicResource>> getAllTopics() {
        var query = new GetAllTopicsQuery();
        var topics = topicQueryService.handle(query);
        var resources = topics.stream()
                .map(TopicResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Obtener un tema por ID",
            description = "Recupera los detalles de un tema por su ID",
            operationId = "getTopicById",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tema encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tema no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{topicId}")
    public ResponseEntity<TopicResource> getTopicById(@PathVariable Long topicId) {
        var query = new GetTopicByIdQuery(topicId);
        var optionalTopic = topicQueryService.handle(query);

        if (optionalTopic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var resource = TopicResourceFromEntityAssembler.toResourceFromEntity(optionalTopic.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(
            summary = "Crear un nuevo tema",
            description = "Registrar un nuevo tema en el sistema",
            operationId = "createTopic",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Tema creado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicResource.class)
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
    public ResponseEntity<TopicResource> createTopic(@RequestBody CreateTopicResource resource) {
        var createCommand = CreateTopicCommandFromResourceAssembler.toCommandFromResource(resource);
        var topicId = topicCommandService.handle(createCommand);

        if (topicId == null || topicId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getTopicByIdQuery = new GetTopicByIdQuery(topicId);
        var optionalTopic = topicQueryService.handle(getTopicByIdQuery);

        if (optionalTopic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topicResource = TopicResourceFromEntityAssembler.toResourceFromEntity(optionalTopic.get());
        return new ResponseEntity<>(topicResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un tema",
            description = "Actualiza los detalles de un tema",
            operationId = "updateTopic",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tema actualizado exitosamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de solicitud inválidos",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{topicId}")
    public ResponseEntity<TopicResource> updateTopic(@PathVariable Long topicId, @RequestBody TopicResource resource) {
        var updateCommand = UpdateTopicCommandFromResourceAssembler.toCommandFromResource(topicId, resource);
        var optionalTopic = topicCommandService.handle(updateCommand);

        if (optionalTopic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var updatedResource = TopicResourceFromEntityAssembler.toResourceFromEntity(optionalTopic.get());
        return ResponseEntity.ok(updatedResource);
    }

    @Operation(
            summary = "Eliminar un tema",
            description = "Elimina un tema del sistema",
            operationId = "deleteTopic",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Tema eliminado exitosamente"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ID inválido suministrado",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long topicId) {
        var deleteCommand = new DeleteTopicCommand(topicId);
        topicCommandService.handle(deleteCommand);
        return ResponseEntity.noContent().build();
    }
}
