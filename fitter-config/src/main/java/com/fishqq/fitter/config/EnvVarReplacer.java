package com.fishqq.fitter.config;

public class EnvVarReplacer {
    private int startOfNotKey;
    private StringBuilder builder;
    private final String value;

    public EnvVarReplacer(String value) {
        this.value = value;
    }

    public String replace() {
        if (value == null || value.isEmpty()) {
            return value;
        }

        int i;
        for (i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '$' && (i == 0 || value.charAt(i - 1) != '\\')) {
                if (i == value.length() - 1) {
                    throw new RuntimeException("bad env var format, missing var name: " + value);
                } else if (value.charAt(i + 1) == '{') {
                    int endOfLastNotKey = i - 1;
                    int start = ++i;
                    boolean match = false;
                    for (; i < value.length(); i++) {
                        if (value.charAt(i) == '}') {
                            replaceWithEnvValue(start, i, endOfLastNotKey);
                            match = true;
                            break;
                        }
                    }
                    if (!match) {
                        throw new RuntimeException("bad env var format, missing }: " + value);
                    }
                } else {
                    int endOfLastNotKey = i;
                    int start = ++i;
                    boolean match = false;
                    for (; i < value.length(); i++) {
                        if (value.charAt(i) == '$' && value.charAt(i - 1) != '\\') {
                            replaceWithEnvValue(start, i, endOfLastNotKey);
                            i--;
                            match = true;
                            break;
                        } else if (i == value.length() - 1) {
                            replaceWithEnvValue(start, i + 1, endOfLastNotKey);
                            match = true;
                            break;
                        }
                    }

                    if (!match) {
                        throw new RuntimeException("bad env var format, missing var name: " + value);
                    }
                }
            }
        }

        if (builder == null) {
            return value;
        } else {
            if (startOfNotKey < value.length()) {
                builder.append(value.substring(startOfNotKey));
            }

            return builder.toString();
        }
    }

    private void replaceWithEnvValue(int startOfKey, int endOfKey, int endOfLastNotKey) {
        if (builder == null) {
            builder = new StringBuilder(value.length());
        }
        if (endOfLastNotKey > startOfNotKey) {
            builder.append(value, startOfNotKey, endOfLastNotKey);
        }
        if (startOfKey == endOfKey) {
            throw new RuntimeException("bad config value: missing env var");
        }
        startOfNotKey = endOfKey + 1;
        String key = value.substring(startOfKey, endOfKey);
        String envValue = System.getenv(key);
        if (envValue == null) {
            throw new RuntimeException(String.format("can't find env var %s in config: %s", key, value));
        } else {
            builder.append(envValue);
        }
    }
}
