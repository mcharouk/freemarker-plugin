package com.fdr.deploy.utils;

import com.fdr.deploy.sink.DeployFile;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterUtils {

    private static final String rootFolder = "target/fdr/deploy";

    public static void closeWriter(String templateName, Writer writer) throws MojoFailureException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Could not close writer for template %s", templateName), e);
        }
    }

    public static Writer getFileWriter(DeployFile deployFile) throws MojoFailureException {
        String fileLocation = String.format("%s/%s", rootFolder, deployFile.getFileName());
        File outputFile = createFile(fileLocation);

        try {
            return new FileWriter(outputFile);
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Error while creating file writer for file %s", fileLocation), e);
        }
    }

    private static File createFile(String fileLocation) throws MojoFailureException {
        File outputFile = new File(fileLocation);

        try {
            FileUtils.forceMkdirParent(outputFile);
        } catch (IOException e) {
            throw new MojoFailureException(String.format("Error while creating file %s", fileLocation), e);
        }
        return outputFile;
    }
}
