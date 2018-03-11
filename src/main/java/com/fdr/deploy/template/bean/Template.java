package com.fdr.deploy.template.bean;

import java.util.Map;

public interface Template {

    String getName();
    Map<String, Object> getParameters();
    String getOutputFileName();
}
