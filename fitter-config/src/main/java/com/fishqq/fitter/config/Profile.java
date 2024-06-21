package com.fishqq.fitter.config;

import java.util.Optional;

public enum Profile {
    prod,
    pre,
    dev,
    test,
    unit_test,
    ;

    public static String orDefault(String profile) {
        return profile == null || profile.isEmpty() ? DEFAULT.name() : profile;
    }

    public static final Profile DEFAULT = Profile.prod;

    private static final String ACTIVE_PROFILE_KEY = "profiles.active";

    public static String activeOrDefault() {
        return active().orElse(DEFAULT.name());
    }

    private static String active;

    public static Optional<String> active() {
        if (active != null) {
            return Optional.of(active);
        }

        String value = System.getenv(ACTIVE_PROFILE_KEY);
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        } else {
            active = value.trim();
            return active();
        }
    }

    public static void setActive(String active) {
        Profile.active = active;
    }

    public static String getProfileFile(String profile, String path) {
        int lastDotIndex = path.lastIndexOf(".");
        return String.format(
                "%s-%s%s",
                path.substring(0, lastDotIndex),
                profile,
                path.substring(lastDotIndex));
    }
}
