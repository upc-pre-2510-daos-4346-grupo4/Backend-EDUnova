package pe.edu.upc.center.edunova.studying.interfaces.rest.resources;

import java.util.List;

public record UpdateLearningProgressResource(
        List<Long> purchasedCourses,
        List<LearningProgressResource.CompletedTopicResource> completedTopics
) {}