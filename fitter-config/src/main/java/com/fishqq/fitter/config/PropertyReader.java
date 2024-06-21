package com.fishqq.fitter.config;

import java.util.Properties;
import java.util.function.Function;

public class PropertyReader {
    private final Properties properties;

    public PropertyReader(Properties properties) {
        this.properties = properties;
    }

    public String readString(String key) {
        return parseValue(key, false);
    }

    public String tryReadString(String key) {
        return tryReadString(key, null);
    }

    public String tryReadString(String key, String defaultValue) {
        String value = parseValue(key, true);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public boolean readBoolean(String key) {
        return readAndConvert(key, "Boolean", Boolean::parseBoolean);
    }

    public Boolean tryReadBoolean(String key, Boolean defaultValue) {
        return tryReadAndConvert(key, defaultValue, "Boolean", Boolean::parseBoolean);
    }

    public Boolean tryReadBoolean(String key) {
        return tryReadBoolean(key, null);
    }

    public double readFloat(String key) {
        return readAndConvert(key, "Float", Float::parseFloat);
    }

    public Float tryReadFloat(String key, Float defaultValue) {
        return tryReadAndConvert(key, defaultValue, "Float", Float::parseFloat);
    }

    public Float tryReadFloat(String key) {
        return tryReadFloat(key, null);
    }

    public double readDouble(String key) {
        return readAndConvert(key, "Double", Double::parseDouble);
    }

    public Double tryReadDouble(String key, Double defaultValue) {
        return tryReadAndConvert(key, defaultValue, "Double", Double::parseDouble);
    }

    public Double tryReadDouble(String key) {
        return tryReadDouble(key, null);
    }

    public long readLong(String key) {
        return readAndConvert(key, "Long", Long::parseLong);
    }

    public Long tryReadLong(String key, Long defaultValue) {
        return tryReadAndConvert(key, defaultValue, "Long", Long::parseLong);
    }

    public Long tryReadLong(String key) {
        return tryReadLong(key, null);
    }

    public int readInt(String key) {
        return readAndConvert(key, "Integer", Integer::parseInt);
    }

    public Integer tryReadInt(String key, Integer defaultValue) {
        return tryReadAndConvert(key, defaultValue, "Integer", Integer::parseInt);
    }

    public Integer tryReadInt(String key) {
        return tryReadInt(key, null);
    }

    public <T> T readAndConvert(String key, String type, Function<String, T> converter) {
        String value = parseValue(key, false);

        try {
            return converter.apply(value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("config %s=%s is not %s: %s", key, value, type, e.getMessage()));
        }
    }

    public <T> T tryReadAndConvert(String key, String type, Function<String, T> converter) {
        return tryReadAndConvert(key, null, type, converter);
    }

    public <T> T tryReadAndConvert(String key, T defaultValue, String type, Function<String, T> converter) {
        String value = parseValue(key, true);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }

        try {
            return converter.apply(value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("config %s=%s is not %s: %s", key, value, type, e.getMessage()));
        }
    }

    private String parseValue(String key, boolean allowEmpty) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            return value;
        }

        value = value.trim();

        String newValue = new EnvVarReplacer(value).replace().trim();

        assertNotEmpty(allowEmpty, key, newValue);

        return newValue;
    }

    private void assertNotEmpty(boolean allowEmpty, String key, String value) {
        if (allowEmpty) {
            return;
        }

        if (value == null || value.isEmpty()) {
            throw new RuntimeException("missing config value: " + key);
        }
    }
}
