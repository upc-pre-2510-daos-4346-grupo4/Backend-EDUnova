package pe.edu.upc.center.edunova.publishing.domain.model.valueobjects;

import java.util.Arrays;

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

    public String getDisplayName() {
        return displayName;
    }

    public static Language fromString(String value) {
        if (value == null) throw new IllegalArgumentException("Language is null");

        try {
            return Language.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException ignored) {}

        return Arrays.stream(values())
                .filter(l -> l.displayName.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown language: " + value));
    }

    @Override
    public String toString() {
        return displayName;
    }
}