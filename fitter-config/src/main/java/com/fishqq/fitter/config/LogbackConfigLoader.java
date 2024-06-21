package com.fishqq.fitter.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Optional;

public class LogbackConfigLoader {
    public static final String DEFAULT_FILE = "logback.xml";

    public static void loadActiveOrDefault() throws JoranException {
        boolean loaded = loadActive();
        if (loaded) {
            return;
        }

        loaded = loadDefault();
        if (loaded) {
            return;
        }

        throw new RuntimeException("can't load logback config");
    }

    public static boolean loadDefault() throws JoranException {
        return load(DEFAULT_FILE);
    }

    public static boolean loadActive() throws JoranException {
        Optional<String> active = Profile.active();
        if (active.isPresent()) {
            boolean loaded = loadByProfile(active.get());
            if (!loaded) {
                throw new RuntimeException(String.format(
                        "can't load active profile logback config: %s %s",
                        active.get(),
                        DEFAULT_FILE));
            }
            return true;
        } else {
            return loadByProfile(Profile.DEFAULT.name());
        }
    }

    public static boolean loadByProfile(String profile) throws JoranException {
        return loadByProfile(Profile.orDefault(profile), DEFAULT_FILE);
    }

    public static boolean loadByProfile(String profile, String path) throws JoranException {
        String fileName = Profile.getProfileFile(profile, path);
        return load(fileName);
    }

    public static boolean load(String logbackFileName) throws JoranException {
        if (!logbackFileName.startsWith("/")) {
            logbackFileName = "/" + logbackFileName;
        }

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        InputStream input = LogbackConfigLoader.class.getResourceAsStream(logbackFileName);
        if (input == null) {
            System.out.println("can't find logback file: " + logbackFileName);
            return false;
        }

        configurator.doConfigure(input);

        return true;
    }
}
