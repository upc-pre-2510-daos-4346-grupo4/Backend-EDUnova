package pe.edu.upc.center.edunova.studying.domain.model.commands;

import lombok.Getter;
import java.util.List;

@Getter
public class UpdateLearningProgressCommand {
    private final Long id;
    private final List<Long> purchasedCourses;
    private final List<CompletedTopicDto> completedTopics;
    public UpdateLearningProgressCommand(Long id, List<Long> purchasedCourses, List<CompletedTopicDto> completedTopics){
        this.id = id;
        this.purchasedCourses = purchasedCourses;
        this.completedTopics = completedTopics;
    }
    @Getter public static class CompletedTopicDto {
        private final Long courseId;
        private final List<Long> topicIds;
        public CompletedTopicDto(Long courseId, List<Long> topicIds){
            this.courseId = courseId; this.topicIds = topicIds;
        }
    }
}