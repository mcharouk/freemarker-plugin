package com.fdr.deploy.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaceholderUtils {

    public static String getApplicationPlaceholder(String applicationName) {
        String applicationNamePlaceholder = String.format("{{application.%s.name}}", applicationName);
        log.info(String.format("creating application name placeholder : %s", applicationNamePlaceholder));
        return applicationNamePlaceholder;
    }

    public static String getEnvironmentPlaceholder(String mode) {
        if (mode.equals("dev")) {
            return "dev";
        }

        return "{{environment}}";
    }


}
