package com.fdr.deploy.template.writer;

import com.fdr.deploy.template.bean.Template;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TemplateFileProcessor implements TemplateProcessor {

    private static final String rootFolder = "target/fdr/deploy";

    @Override
    public void closeWriter(Template template, Writer writer) throws MojoFailureException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Could not close writer for template %s", template.getOutputFileName()), e);
        }
    }

    @Override
    public Writer getWriter(Template template) throws MojoFailureException {
        String fileLocation = String.format("%s/%s", rootFolder, template.getOutputFileName());
        File outputFile = createFile(fileLocation);

        try {
            return new FileWriter(outputFile);
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Error while creating file writer for file %s", fileLocation), e);
        }
    }

    private File createFile(String fileLocation) throws MojoFailureException {
        File outputFile = new File(fileLocation);

        try {
            FileUtils.forceMkdirParent(outputFile);
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Error while creating file %s", fileLocation), e);
        }
        return outputFile;
    }

}
