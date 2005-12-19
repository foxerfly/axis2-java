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
*/


package org.apache.axis2.deployment.repository.util;

import org.apache.axis2.AxisFault;
import org.apache.axis2.deployment.DeploymentClassLoader;
import org.apache.axis2.deployment.DeploymentErrorMsgs;
import org.apache.axis2.deployment.util.Utils;
import org.apache.axis2.i18n.Messages;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * ArchiveFileData = Hot Deployment File Item, stores infromation about the module or service
 * item to be deployed
 */
public class ArchiveFileData {
    private File file = null;
    private ArrayList deploybleServices = new ArrayList();
    private ClassLoader classLoader;
    private String messgeReceiver;

    private String name;
    private int type;

    public ArchiveFileData(File file, int type) {
        this.file = file;
        this.type = type;
    }

    public ArchiveFileData(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public ArrayList getDeploybleServices() {
        return deploybleServices;
    }

    public File getFile() {
        return file;
    }

    public String getMessgeReceiver() {
        return messgeReceiver;
    }

    public String getName() {
        return file.getName();
    }

    public String getServiceName() {
        if (file != null) {
            return file.getName();
        } else {
            return name;
        }
    }

    public int getType() {
        return type;
    }

    public static boolean isModuleArchiveFile(String filename) {
        return ((filename.endsWith(".jar")) || (filename.endsWith(".mar")));
    }

    /**
     * to check whthere a given file is  a  jar file
     *
     * @param filename
     * @return boolean
     */
    public static boolean isServiceArchiveFile(String filename) {
        return ((filename.endsWith(".jar")) | (filename.endsWith(".aar")));
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(boolean extractArichive, ClassLoader parent) throws AxisFault {
        if (!extractArichive) {

            if (file != null) {
                URL[] urlsToLoadFrom;

                try {
                    if (!file.exists()) {
                        throw new AxisFault(Messages.getMessage(DeploymentErrorMsgs.FILE_NOT_FOUND,
                                file.getAbsolutePath()));
                    }

                    urlsToLoadFrom = new URL[]{file.toURL()};
                    classLoader = new DeploymentClassLoader(urlsToLoadFrom, parent);
                } catch (Exception e) {
                    throw new AxisFault(e);
                }
            }
        } else {
            if (file != null) {
                classLoader = Utils.getClassLoader(parent, file);
            }
        }
    }

    public void setDeploybleServices(ArrayList deploybleServices) {
        this.deploybleServices = deploybleServices;
    }

    public void setMessgeReceiver(String messgeReceiver) {
        this.messgeReceiver = messgeReceiver;
    }
}
