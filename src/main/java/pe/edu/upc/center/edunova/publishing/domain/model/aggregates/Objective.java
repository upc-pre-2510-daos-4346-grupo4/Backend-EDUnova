package pe.edu.upc.center.edunova.publishing.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.TopicId;
import pe.edu.upc.center.edunova.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "objectives")
@Getter
@NoArgsConstructor
public class Objective extends AuditableAbstractAggregateRoot<Objective> {
    @Embedded
    @AttributeOverride(name = "topicId", column = @Column(name = "topic_id"))
    private TopicId topicId;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 250)
    private String title;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 250)
    private String header;


    @Column(name = "main_paragraph", nullable = false, length = 1000)
    @NotBlank
    @NotNull
    private String mainParagraph;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 250)
    private String footer;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 250)
    private String conclusion;

    // Constructor
    public Objective(TopicId topicId, String title, String header, String mainParagraph, String footer, String conclusion) {
        this.topicId = topicId;
        this.title = title;
        this.header = header;
        this.mainParagraph = mainParagraph;
        this.footer = footer;
        this.conclusion = conclusion;
    }

    /*Constructor desde CreateOnjectiveCommand
    public Objective(CreateObjectiveCommand command) {
        this(
            command.getTopicId(),
            command.getTitle(),
            command.getHeader(),
            command.getMainParagraph(),
            command.getFooter(),
            command.getConclusion()
        );
    }
    */


    //Método para actualizar los detalles del recurso
    public void update(String title, String header, String mainParagraph, String footer, String conclusion) {
        this.title = title;
        this.header = header;
        this.mainParagraph = mainParagraph;
        this.footer = footer;
        this.conclusion = conclusion;
    }
}
