package com.fdr.deploy.mojo;

import com.fdr.deploy.sink.DeployFile;
import com.fdr.deploy.template.handler.OozieSubmitterTemplateHandler;
import com.fdr.deploy.utils.WriterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Optional;

@Mojo(name = "generate-deploy-files", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
@Slf4j
public class GenerateDeployFilesMojo extends AbstractMojo {

    @Parameter
    private String applicationName;

    @Parameter
    private String mainClassName;

    @Parameter(defaultValue = "dev")
    private String mode;

    @Parameter
    private String[] batchParameters;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        this.applicationName = validateApplicationName(applicationName);
        this.mainClassName = validateMainClassName(mainClassName);

        new OozieSubmitterTemplateHandler().buildFile(mode,
                applicationName,
                mainClassName,
                batchParameters,
                WriterUtils.getFileWriter(DeployFile.OOZIE_SUBMIT));
    }

    private String validateApplicationName(String applicationName) throws MojoExecutionException {
        return Optional.ofNullable(applicationName)
                .orElseThrow(() -> new MojoExecutionException("property application name should be specified"));
    }

    private String validateMainClassName(String mainClassName) throws MojoExecutionException {
        return Optional.ofNullable(mainClassName)
                .orElseThrow(() -> new MojoExecutionException("property main class name should be specified"));
    }


}
