package com.fdr.deploy.utils;

import com.fdr.deploy.template.config.TemplateConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Slf4j
public class TemplateUtils {


    public static void writeTemplateResult(String templateName, Map<String, Object> templateParameters, Writer templateWriter) throws MojoFailureException {
        try {
            Template template = TemplateConfig.getInstance().getConfiguration().getTemplate(templateName);
            template.process(templateParameters, templateWriter);
        } catch (TemplateException | IOException e) {
            throw new MojoFailureException(String.format("Issue while processing template %s", templateName), e);
        } finally {
            WriterUtils.closeWriter(templateName, templateWriter);
        }
    }

}
