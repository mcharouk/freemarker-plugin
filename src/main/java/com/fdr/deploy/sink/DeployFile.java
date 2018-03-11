package com.fdr.deploy.sink;

public enum DeployFile {

    OOZIE_SUBMIT {
        @Override
        public String getFileName() {
            return "oozie-submit.sh";
        }
    };

    public abstract String getFileName();
}
