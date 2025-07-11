package pe.edu.upc.center.edunova.studying.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "learning_progress")
@Getter
@Setter
public class LearningProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    @CollectionTable(
            name = "learning_progress_purchased_courses",
            joinColumns = @JoinColumn(name = "progress_id")
    )
    @Column(name = "purchased_course_id")
    private List<Long> purchasedCourses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "learning_progress_completed_topics",
            joinColumns = @JoinColumn(name = "progress_id")
    )
    private List<CompletedTopic> completedTopics = new ArrayList<>();

    public void replacePurchasedCourses(List<Long> newList){
        this.purchasedCourses.clear();
        this.purchasedCourses.addAll(newList);
    }

    public void replaceCompletedTopics(List<CompletedTopic> newList){
        this.completedTopics.clear();
        this.completedTopics.addAll(newList);
    }

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class CompletedTopic {

        @Column(name = "course_id")
        private Long courseId;

        @Column(name = "topic_ids")
        private String topicIdsSerialized;

        @Transient
        public List<Long> getTopicIds() {
            if (topicIdsSerialized == null || topicIdsSerialized.isBlank()) return new ArrayList<>();
            return java.util.Arrays.stream(topicIdsSerialized.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .map(Long::valueOf)
                    .toList();
        }
        public void setTopicIds(List<Long> ids){
            this.topicIdsSerialized = (ids == null || ids.isEmpty())
                    ? ""
                    : ids.stream().map(String::valueOf).reduce((a,b)->a+","+b).orElse("");
        }
    }
}
