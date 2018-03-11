package com.fdr.deploy.template.bean;

import lombok.Getter;

@Getter
public class BatchParameter {

    private final String parameterName;
    private final int parameterNumber;

    public BatchParameter(String parameterName, int parameterNumber) {
        this.parameterName = parameterName;
        this.parameterNumber = parameterNumber;
    }
}
