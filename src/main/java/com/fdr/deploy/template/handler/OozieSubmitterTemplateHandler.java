package com.fdr.deploy.template.handler;

import com.fdr.deploy.template.bean.BatchParameter;
import com.fdr.deploy.utils.PlaceholderUtils;
import com.fdr.deploy.utils.TemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.MojoFailureException;

import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class OozieSubmitterTemplateHandler {

    private final String templateName = "oozie-submit.ftl";

    public void buildFile(String mode, String applicationName, String mainClassName, String[] batchParameters, Writer writer) throws MojoFailureException {
        Map<String, Object> templateParameter = initializeParameters(mode, applicationName, mainClassName, batchParameters);
        TemplateUtils.writeTemplateResult(templateName, templateParameter, writer);
    }

    private Map<String, Object> initializeParameters(String mode, String applicationName, String mainClassName, String[] batchParameters) {

        Map<String, Object> input = new HashMap<>();

        input.put("applicationName", PlaceholderUtils.getApplicationPlaceholder(applicationName));
        input.put("mainClassName", mainClassName);

        initBatchParameters(batchParameters, input);
        input.put("environment", PlaceholderUtils.getEnvironmentPlaceholder(mode));

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
