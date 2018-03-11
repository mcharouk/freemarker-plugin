package com.fdr.deploy.template.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;

@Getter
public class TemplateConfig {

    private final Configuration configuration;

    private TemplateConfig() {

        this.configuration = new Configuration(Configuration.VERSION_2_3_23);

        // Where do we load the templates from:
        ClassTemplateLoader loader = new ClassTemplateLoader(
                this.getClass(), "/templates");

        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }


    private static TemplateConfig INSTANCE = new TemplateConfig();


    public static TemplateConfig getInstance() {
        return INSTANCE;
    }

}
