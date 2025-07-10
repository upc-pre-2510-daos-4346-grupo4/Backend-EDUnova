package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

public enum CourseCategory {
    MATHEMATICS("Mathematics"),
    COMPUTER_SCIENCE("Computer Science"),
    SCIENCE("Science"),
    ARTS("Arts"),
    DESIGN("Design"),
    BUSINESS("Business"),
    LANGUAGES("Languages"),
    HUMANITIES("Humanities"),
    HEALTH_AND_WELLNESS("Health & Wellness"),
    PERSONAL_DEVELOPMENT("Personal Development");

    private final String displayName;

    CourseCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}