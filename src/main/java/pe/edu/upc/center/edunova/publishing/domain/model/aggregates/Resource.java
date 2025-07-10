package pe.edu.upc.center.edunova.publishing.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.CourseId;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "resources")
@Getter
@NoArgsConstructor
public class Resource extends AuditableAbstractAggregateRoot<Resource> {
    @Embedded
    @AttributeOverride(name = "topicId", column = @Column(name = "topic_id"))
    private TopicId topicId;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 50)
    private String youtubeId;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 250)
    private String title;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 1000)
    private String description;

    //Constructor completo
    public Resource(TopicId topicId, String youtubeId, String title, String description) {
        this.topicId = topicId;
        this.youtubeId = youtubeId;
        this.title = title;
        this.description = description;
    }

    /*Constructor desde CreateResourceCommand

    public Resource(CreateResourceCommand command) {
        this(
            command.getTopicId(),
            command.getYoutubeId(),
            command.getTitle(),
            command.getDescription()
        );
    }
    */
    //Método para actualizar los detalles del recurso
    public void update(String youtubeId, String title, String description) {
        this.youtubeId = youtubeId;
        this.title = title;
        this.description = description;
    }

}
