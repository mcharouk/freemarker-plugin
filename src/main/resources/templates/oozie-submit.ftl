#!/bin/bash
applicationName=${environment}-${applicationName}
className=${mainClassName}
sparkMaster=yarn-cluster
<#list batchParameters as batchParameter>
${batchParameter.parameterName}=$${batchParameter.parameterNumber}
</#list>
