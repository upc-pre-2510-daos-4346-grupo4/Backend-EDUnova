package pe.edu.upc.center.edunova.publishing.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;
import pe.edu.upc.center.edunova.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
public class Topic extends AuditableAbstractAggregateRoot<Topic> {

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 250)
    private String title;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 1000)
    private String description;

    @Embedded
    @AttributeOverride(name = "courseId", column = @Column(name = "course_id"))
    private CourseId courseId;

    // Relationships
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    private List<Resource> resources = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    private List<Objective> objectives = new ArrayList<>();

    //Constructor completo
    public Topic(String title, String description, CourseId courseId,
                 List<Resource> resources, List<Objective> objectives) {
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.resources = resources;
        this.objectives = objectives;
    }

    /* Constructor desde CreateTopicCommand
    public Topic(CreateTopicCommand command) {
        this(command.getTitle(),
                command.getDescription(),
                command.getCourseId(),
                new ArrayList<>()
                );
    }
    */

    // Método para actualizar información básica
    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Métodos de dominio
    public void addResource(Resource resource) {
        this.resources.add(resource);
    }
    public void addObjective(Objective objective) {
        this.objectives.add(objective);
    }
    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }
    public void removeObjective(Objective objective) {
        this.objectives.remove(objective);
    }
    public void removeObjectiveById(Long objectiveId) {
        this.objectives.removeIf(objective -> objective.getId().equals(objectiveId));
    }
    public void removeResourceById(Long resourceId) {
        this.resources.removeIf(resource -> resource.getId().equals(resourceId));
    }

}
