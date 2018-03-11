package com.fdr.deploy.template.provider;

import com.fdr.deploy.template.bean.BatchParameter;
import com.fdr.deploy.template.bean.SimpleTemplate;
import com.fdr.deploy.template.bean.Template;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class OozieSubmitterTemplateProvider implements TemplateProvider {

    private final String mode;
    private final String applicationName;
    private final String mainClassName;
    private final String[] batchParameters;

    private final String templateName = "oozie-submit.ftl";
    private final String outputFileName = "oozie-submit.sh";

    public OozieSubmitterTemplateProvider(String mode, String applicationName, String mainClassName, String[] batchParameters) {
        this.mode = mode;
        this.applicationName = applicationName;
        this.mainClassName = mainClassName;
        this.batchParameters = batchParameters;
    }

    @Override
    public Template getTemplate() {
        Map<String, Object> templateParameter = initializeParameters(mode, applicationName, mainClassName, batchParameters);
        return new SimpleTemplate(templateName, outputFileName, templateParameter);
    }

    private Map<String, Object> initializeParameters(String mode, String applicationName, String mainClassName, String[] batchParameters) {

        Map<String, Object> input = new HashMap<>();

        String applicationNamePlaceholder = getApplicationPlaceholder(applicationName);
        log.info(String.format("creating application name placeholder : %s", applicationNamePlaceholder));
        input.put("applicationName", applicationNamePlaceholder);

        input.put("mainClassName", mainClassName);

        initBatchParameters(batchParameters, input);
        input.put("environment", getEnvironmentPlaceholder(mode));

        return input;
    }

    private void initBatchParameters(String[] batchParameters, Map<String, Object> input) {

        List<BatchParameter> batchParameterList = Optional.ofNullable(batchParameters)
                .flatMap(this::createBatchParameterList)
                .orElseGet(ArrayList::new);

        input.put("batchParameters", batchParameterList);
    }

    private Optional<List<BatchParameter>> createBatchParameterList(String[] batchParameters) {
        return Optional.of(
                IntStream.range(0, batchParameters.length)
                        .mapToObj(i -> new BatchParameter(batchParameters[i], i + 1))
                        .collect(Collectors.toList())
        );
    }


}
