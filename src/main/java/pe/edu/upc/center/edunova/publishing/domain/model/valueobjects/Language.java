package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

public enum Language {
    ENGLISH("English"),
    SPANISH("Spanish"),
    MANDARIN_CHINESE("Mandarin Chinese"),
    HINDI("Hindi"),
    FRENCH("French"),
    ARABIC("Arabic"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    GERMAN("German"),
    JAPANESE("Japanese");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        //return displayName;
        return name();
    }
}
