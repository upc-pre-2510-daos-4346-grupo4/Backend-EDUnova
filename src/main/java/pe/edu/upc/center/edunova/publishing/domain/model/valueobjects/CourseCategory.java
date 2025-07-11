package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import java.util.Arrays;

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

    CourseCategory(String displayName) { this.displayName = displayName; }

    public String getDisplayName() { return displayName; }

    public static CourseCategory fromString(String value) {
        if (value == null) throw new IllegalArgumentException("Category is null");
        try { return CourseCategory.valueOf(value.toUpperCase().replace(" ", "_")); }
        catch (IllegalArgumentException ignored) {}
        return Arrays.stream(values())
                .filter(c -> c.displayName.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown category: " + value));
    }

    @Override public String toString() { return displayName; }
}