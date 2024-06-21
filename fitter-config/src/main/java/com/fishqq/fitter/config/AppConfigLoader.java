package com.fishqq.fitter.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public abstract class AppConfigLoader {
    public abstract String defaultFileName();

    public Optional<String> loadDefault() throws IOException {
        return load(defaultFileName());
    }

    public Optional<String> loadActive() throws IOException {
        Optional<String> profile = Profile.active();
        if (profile.isPresent()) {
            Optional<String> text = loadByProfile(profile.get(), defaultFileName());
            if (!text.isPresent()) {
                throw new RuntimeException(String.format(
                        "can't load active profile config: %s %s",
                        profile.get(),
                        defaultFileName()));
            }
            return text;
        } else {
            return loadByProfile(Profile.DEFAULT.name());
        }
    }

    public Optional<String> loadByProfile(String profile) throws IOException {
        return loadByProfile(profile, defaultFileName());
    }

    public Optional<String> loadByProfile(String profile, String path) throws IOException {
        return load(Profile.getProfileFile(profile, path));
    }

    public Optional<String> load(String path) throws IOException {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        try (InputStream in = ResourceFileUtil.class.getResourceAsStream(path)) {
            if (in == null) {
                return Optional.empty();
            }
            return Optional.of(new EnvVarReplacer(IOUtil.toString(in, StandardCharsets.UTF_8)).replace());
        }
    }
}
