package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;


public enum Difficulty {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String displayName;

    Difficulty(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Difficulty fromDisplayName(String displayName) {
        for (Difficulty diff : values()) {
            if (diff.displayName.equalsIgnoreCase(displayName)) {
                return diff;
            }
        }
        throw new IllegalArgumentException("Nivel de dificultad no soportado: " + displayName);
    }
}
