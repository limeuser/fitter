package com.fishqq.fitter.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class AppPropertiesLoader {
    public static final String DEFAULT_FILE = "application.properties";

    public static Properties loadDefaultAndActive() throws IOException {
        Properties merged = new Properties();
        Optional<Properties> defaultProperties = loadDefault();
        defaultProperties.ifPresent(merged::putAll);
        Optional<Properties> profileProperties = loadActive();
        profileProperties.ifPresent(merged::putAll);

        if (!defaultProperties.isPresent() && !profileProperties.isPresent()) {
            throw new RuntimeException("can't load application config in default and profile file");
        }

        for (String name : merged.stringPropertyNames()) {
            String value = merged.getProperty(name);
            String replaced = new EnvVarReplacer(value).replace();
            if (value != replaced) {
                merged.put(name, replaced);
            }
        }

        return merged;
    }

    public static Optional<Properties> loadDefault() throws IOException {
        return load(DEFAULT_FILE);
    }

    public static Optional<Properties> loadActive() throws IOException {
        Optional<String> active = Profile.active();
        if (active.isPresent()) {
            Optional<Properties> properties = loadByProfile(active.get());
            if (!properties.isPresent()) {
                throw new RuntimeException(String.format(
                        "can't load active profile config: %s %s",
                        active.get(),
                        DEFAULT_FILE));
            }
            return properties;
        } else {
            return loadByProfile(Profile.DEFAULT.name());
        }
    }

    public static Optional<Properties> loadByProfile(String profile) throws IOException {
        return loadByProfile(profile, DEFAULT_FILE);
    }

    public static Optional<Properties> loadByProfile(String profile, String path) throws IOException {
        String fileName = Profile.getProfileFile(profile, path);
        return load(fileName);
    }

    public static Optional<Properties> load(String path) throws IOException {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        try (InputStream in = AppPropertiesLoader.class.getResourceAsStream(path)) {
            if (in == null) {
                return Optional.empty();
            }

            Properties properties = new Properties();
            properties.load(in);
            return Optional.of(properties);
        }
    }
}
