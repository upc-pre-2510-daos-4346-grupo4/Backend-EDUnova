package pe.edu.upc.center.edunova.studying.interfaces.rest.resources;

import java.util.List;

public record LearningProgressResource(
        Long id,
        Long userId,
        List<Long> purchasedCourses,
        List<CompletedTopicResource> completedTopics
) {
    public record CompletedTopicResource(Long courseId, List<Long> topicIds) {}
}