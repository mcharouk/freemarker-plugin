package com.fdr.deploy.template.provider;

import com.fdr.deploy.template.bean.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.MojoFailureException;

public interface TemplateProvider {

    Template getTemplate() throws MojoFailureException;

    default String getApplicationPlaceholder(String applicationName) {
        return String.format("{{application.%s.name}}", applicationName);
    }

    default String getEnvironmentPlaceholder(String mode) {
        if (mode.equals("dev")) {
            return "dev";
        }

        return "{{environment}}";
    }
}
