package pe.edu.upc.center.edunova.studying.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class LearningProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    @CollectionTable(name = "learning_progress_purchased_courses", joinColumns = @JoinColumn(name = "progress_id"))
    @Column(name = "course_id")
    private List<Long> purchasedCourses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "learning_progress_completed_topics", joinColumns = @JoinColumn(name = "progress_id"))
    private List<CompletedTopic> completedTopics = new ArrayList<>();

    @Embeddable
    @Getter
    @Setter
    public static class CompletedTopic {
        private Long courseId;

        @Column(name = "topic_ids")
        private String topicIdsSerialized;

        @Transient
        public List<Long> getTopicIds() {
            if (topicIdsSerialized == null || topicIdsSerialized.isEmpty()) return new ArrayList<>();
            String[] parts = topicIdsSerialized.split(",");
            List<Long> ids = new ArrayList<>();
            for (String part : parts) {
                try {
                    ids.add(Long.parseLong(part.trim()));
                } catch (NumberFormatException ignored) {}
            }
            return ids;
        }

        public void setTopicIds(List<Long> topicIds) {
            if (topicIds == null || topicIds.isEmpty()) {
                this.topicIdsSerialized = "";
            } else {
                this.topicIdsSerialized = topicIds.stream().map(String::valueOf).reduce((a, b) -> a + "," + b).orElse("");
            }
        }
    }
}