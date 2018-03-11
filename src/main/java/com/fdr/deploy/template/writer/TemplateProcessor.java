package com.fdr.deploy.template.writer;

import com.fdr.deploy.template.bean.Template;
import com.fdr.deploy.template.config.TemplateConfig;
import freemarker.template.TemplateException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;
import java.io.Writer;

public interface TemplateProcessor {

    default void process(Template template) throws MojoFailureException {
        Writer templateWriter = getWriter(template);
        try {
            freemarker.template.Template freeMarkerTemplate = TemplateConfig.getInstance().getConfiguration().getTemplate(template.getName());
            freeMarkerTemplate.process(template.getParameters(), templateWriter);
        } catch (TemplateException | IOException e) {
            throw new MojoFailureException(String.format("Issue while processing template %s", template.getName()), e);
        } finally {
            closeWriter(template, templateWriter);
        }
    }


    void closeWriter(Template template, Writer writer) throws MojoFailureException;

    Writer getWriter(Template template) throws MojoFailureException;
}
