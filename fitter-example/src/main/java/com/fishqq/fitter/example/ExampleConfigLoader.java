package com.fishqq.fitter.example;

import com.fishqq.fitter.config.AppConfigLoader;

public class ExampleConfigLoader extends AppConfigLoader {
    @Override
    public String defaultFileName() {
        return "application.yaml";
    }
}
