package com.fishqq.fitter.config;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

public class PropertyWriter {
    private final Properties properties;

    public PropertyWriter() {
        this.properties = new Properties();
    }

    public PropertyWriter(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public PropertyWriter merge(Map<String, String> properties) {
        this.properties.putAll(properties);
        return this;
    }

    public PropertyWriter writeString(String key, String value) {
        if (value == null) {
            throw new RuntimeException("value is null: " + key);
        }

        properties.setProperty(key, value);

        return this;
    }

    public PropertyWriter tryWriteString(String key, String value) {
        if (value != null) {
            properties.setProperty(key, value);
        }

        return this;
    }

    public PropertyWriter tryWriteString(String key, String value, String defaultValue) {
        properties.setProperty(key, Optional.ofNullable(value).orElse(defaultValue));

        return this;
    }

    public PropertyWriter writeBoolean(String key, String value) {
        return writeAndConvert(key, value, "Boolean", Boolean::parseBoolean);
    }

    public PropertyWriter tryWriteBoolean(String key, String value, boolean defaultValue) {
        return tryWriteAndConvert(key, value, defaultValue, "Boolean", Boolean::parseBoolean);
    }

    public PropertyWriter tryWriteBoolean(String key, String value) {
        return tryWriteAndConvert(key, value, "Boolean", Boolean::parseBoolean);
    }

    public PropertyWriter writeFloat(String key, String value) {
        return writeAndConvert(key, value, "Float", Float::parseFloat);
    }

    public PropertyWriter tryWriteFloat(String key, String value, Float defaultValue) {
        return tryWriteAndConvert(key, value, defaultValue, "Float", Float::parseFloat);
    }

    public PropertyWriter tryWriteFloat(String key, String value) {
        return tryWriteAndConvert(key, value, "Float", Float::parseFloat);
    }

    public PropertyWriter writeDouble(String key, String value) {
        return writeAndConvert(key, value, "Double", Double::parseDouble);
    }

    public PropertyWriter tryWriteDouble(String key, String value, Double defaultValue) {
        return tryWriteAndConvert(key, value, defaultValue, "Double", Double::parseDouble);
    }

    public PropertyWriter tryWriteDouble(String key, String value) {
        return tryWriteAndConvert(key, value, "Double", Double::parseDouble);
    }

    public PropertyWriter writeLong(String key, String value) {
        return writeAndConvert(key, value, "Long", Long::parseLong);
    }

    public PropertyWriter tryWriteLong(String key, String value, Long defaultValue) {
        return tryWriteAndConvert(key, value, defaultValue, "Long", Long::parseLong);
    }

    public PropertyWriter tryWriteLong(String key, String value) {
        return tryWriteAndConvert(key, value, "Long", Long::parseLong);
    }

    public PropertyWriter writeInt(String key, String value) {
        return writeAndConvert(key, value, "Integer", Integer::parseInt);
    }

    public PropertyWriter tryWriteInt(String key, String value, Integer defaultValue) {
        return tryWriteAndConvert(key, value, defaultValue, "Integer", Integer::parseInt);
    }

    public PropertyWriter tryWriteInt(String key, String value) {
        return tryWriteAndConvert(key, value, "Integer", Integer::parseInt);
    }

    public <T> PropertyWriter writeAndConvert(String key, String value, String type, Function<String, T> converter) {
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("missing config value: " + key);
        }

        try {
            T parsedValue = converter.apply(value.trim());
            properties.put(key, parsedValue);
        } catch (Exception e) {
            throw new RuntimeException(String.format("config %s=%s is not %s: %s", key, value, type, e.getMessage()));
        }

        return this;
    }

    public PropertyWriter tryWrite(String key, Object value) {
        if (value == null) {
            return this;
        }

        this.properties.put(key, value);

        return this;
    }

    public <T, R> PropertyWriter tryWriteAndConvert(String key, T value, Function<T, R> converter) {
        if (value == null) {
            return this;
        }

        this.properties.put(key, converter.apply(value));

        return this;
    }

    public <T> PropertyWriter tryWriteAndConvert(String key, String value, String type, Function<String, T> converter) {
        if (value == null || value.isEmpty()) {
            return this;
        }

        return tryWriteAndConvert(key, value, null, type, converter);
    }

    public <T> PropertyWriter tryWriteAndConvert(String key,
                                                 String value,
                                                 T defaultValue,
                                                 String type,
                                                 Function<String, T> converter) {
        if (value == null || value.isEmpty()) {
            properties.put(key, defaultValue);
            return this;
        }

        try {
            T parsedValue = Optional.ofNullable(converter.apply(value.trim())).orElse(defaultValue);
            properties.put(key, parsedValue);
        } catch (Exception e) {
            throw new RuntimeException(String.format("config %s=%s is not %s: %s", key, value, type, e.getMessage()));
        }

        return this;
    }
}
