package pe.edu.upc.center.edunova.publishing.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.center.edunova.publishing.domain.model.commands.CreateCourseCommand;
import pe.edu.upc.center.edunova.publishing.domain.model.valueobjects.*;
import pe.edu.upc.center.edunova.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
public class Course extends AuditableAbstractAggregateRoot<Course> {

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 250)
    private String name;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 1000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CourseCategory category;


    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    @Embedded
    @AttributeOverride(name = "creatorId", column = @Column(name = "creator_id"))
    private CreatorId creatorId;

    @Embedded
    @Column(nullable = false)
    private ImageUrl image;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Language language;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Difficulty difficulty;

    // Relationships
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<Topic> topics = new ArrayList<>();

    // Constructor completo
    public Course(String name, String description, CourseCategory category,
                   BigDecimal price, CreatorId creatorId, ImageUrl image,
                   Language language, Difficulty difficulty, List<Topic> topics) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.creatorId = creatorId;
        this.image = image;
        this.language = language;
        this.difficulty = difficulty;
        this.topics = topics != null ? topics : new ArrayList<>();
    }

    // Constructor desde CreateCourseCommand
    public Course(CreateCourseCommand command) {
        this(
                command.name(),
                command.description(),
                command.category(),
                command.price(),
                command.creatorId(),
                command.image(),
                command.language(),
                command.difficulty(),
                new ArrayList<>()
        );
    }


    // Método para actualizar información básica
    public void updateInformation(String name, String description, CourseCategory category,
                                  BigDecimal price, ImageUrl image,
                                  Language language, Difficulty difficulty) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.image = image;
        this.language = language;
        this.difficulty = difficulty;
    }

    // Métodos de dominio
    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void removeTopic(Topic topic) {
        this.topics.remove(topic);
    }
    public void clearTopics() {
        this.topics.clear();
    }

    public void removeTopicById(Long topicId) {
        this.topics.removeIf(topic -> topic.getId().equals(topicId));
    }


}
