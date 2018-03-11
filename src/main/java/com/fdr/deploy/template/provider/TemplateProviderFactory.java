package com.fdr.deploy.template.provider;

import java.util.Map;

public class TemplateProviderFactory {

    public TemplateProvider createOozieSubmitterTemplateProvider(String mode, String applicationName, String mainClassName, String[] batchParameters) {
        return new OozieSubmitterTemplateProvider(mode, applicationName, mainClassName, batchParameters);
    }

}
