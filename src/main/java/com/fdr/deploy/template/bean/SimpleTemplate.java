package com.fdr.deploy.template.bean;

import lombok.Getter;

import java.util.Map;

@Getter
public class SimpleTemplate implements Template {

    private final String name;
    private final String outputFileName;
    private final Map<String, Object> parameters;

    public SimpleTemplate(String name,  String outputFileName, Map<String, Object> parameters) {
        this.name = name;
        this.outputFileName = outputFileName;
        this.parameters = parameters;
    }
}
