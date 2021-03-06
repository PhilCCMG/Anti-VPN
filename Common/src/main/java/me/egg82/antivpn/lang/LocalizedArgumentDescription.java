package me.egg82.antivpn.lang;

import cloud.commandframework.ArgumentDescription;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class LocalizedArgumentDescription implements ArgumentDescription {
    public static ArgumentDescription of(@NotNull MessageKey key) {
        return new LocalizedArgumentDescription(Locales.getUS(), key);
    }

    public static ArgumentDescription of(@NotNull MessageKey key, String... placeholders) {
        return new LocalizedArgumentDescription(Locales.getUS(), key, placeholders);
    }

    public static ArgumentDescription of(@NotNull MessageKey key, @NotNull Map<String, String> placeholders) {
        return new LocalizedArgumentDescription(Locales.getUS(), key, placeholders);
    }

    public static ArgumentDescription of(@NotNull I18NManager localizationManager, @NotNull MessageKey key) {
        return new LocalizedArgumentDescription(localizationManager, key);
    }

    public static ArgumentDescription of(@NotNull I18NManager localizationManager, @NotNull MessageKey key, String... placeholders) {
        return new LocalizedArgumentDescription(localizationManager, key, placeholders);
    }

    public static ArgumentDescription of(@NotNull I18NManager localizationManager, @NotNull MessageKey key, @NotNull Map<String, String> placeholders) {
        return new LocalizedArgumentDescription(localizationManager, key, placeholders);
    }

    private final String description;

    private LocalizedArgumentDescription(@NotNull I18NManager localizationManager, @NotNull MessageKey key) {
        this.description = localizationManager.getText(key);
    }

    private LocalizedArgumentDescription(@NotNull I18NManager localizationManager, @NotNull MessageKey key, String... placeholders) {
        this.description = localizationManager.getText(key, placeholders);
    }

    private LocalizedArgumentDescription(@NotNull I18NManager localizationManager, @NotNull MessageKey key, @NotNull Map<String, String> placeholders) {
        this.description = localizationManager.getText(key, placeholders);
    }

    public @NotNull String getDescription() { return description; }

    public boolean isEmpty() { return false; }
}
