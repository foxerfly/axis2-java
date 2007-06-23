package org.apache.axis2.deployment;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.deployment.repository.util.DeploymentFileData;

/*
* Copyright 2004,2005 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*
*/

/**
 * This interface is used to provide the custom deployment mechanism , where you
 * can write your own Deployer to process a particular type and make that to
 * a service or a module.
 */
public interface Deployer {
    /**
     * Initialize the Deployer
     * @param configCtx our ConfigurationContext
     */
    void init(ConfigurationContext configCtx);

    /**
     * Process a file and add it to the configuration
     * @param deploymentFileData the DeploymentFileData object to deploy
     * @throws DeploymentException if there is a problem
     */
    void deploy(DeploymentFileData deploymentFileData) throws DeploymentException;

    /**
     * Set the directory
     * @param directory directory name
     */
    void setDirectory(String directory);

    /**
     * Set the extension to look for
     * TODO: Support multiple extensions?
     * @param extension the file extension associated with this Deployer
     */
    void setExtension(String extension);

    /**
     * Remove a given file from the configuration
     * @param fileName name of item to remove
     * @throws DeploymentException if there is a problem
     */
    void unDeploy(String fileName) throws DeploymentException;
}
