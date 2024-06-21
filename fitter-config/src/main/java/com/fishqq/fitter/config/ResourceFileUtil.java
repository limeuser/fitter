package com.fishqq.fitter.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ResourceFileUtil {
    public static String read(String path) throws IOException {
        try (InputStream in = ResourceFileUtil.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IOException("resource file " + path + " not exists");
            }

            return IOUtil.toString(in, StandardCharsets.UTF_8);
        }
    }

    private static String readFromDir() {
        return "";
    }

    public static Properties readToProperties(String path) throws IOException {
        try (InputStream in = ResourceFileUtil.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IOException("resource file " + path + " not exists");
            }
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        }
    }
}
