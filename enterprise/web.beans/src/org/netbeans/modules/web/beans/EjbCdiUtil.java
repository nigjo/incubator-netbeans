/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.web.beans;

import java.util.Collection;
import java.util.Collections;

import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.api.ejbjar.EjbJar;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.openide.filesystems.FileObject;



/**
 * @author ads
 *
 */
@ProjectServiceProvider(service=CdiUtil.class, projectType = {
    "org-netbeans-modules-j2ee-ejbjarproject", "org-netbeans-modules-maven/ejb"})
public class EjbCdiUtil extends CdiUtil {

    public EjbCdiUtil( Project project ) {
        super(project);
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.web.beans.CdiUtil#getBeansTargetFolder(boolean)
     */
    @Override
    public Collection<FileObject> getBeansTargetFolder(boolean create) 
    {
        Project project = getProject();
        if ( project == null ){
            return Collections.emptyList();
        }
        EjbJar ejbs[] = EjbJar.getEjbJars(project);
        if (ejbs.length > 0) {
            return Collections.singleton(ejbs[0].getMetaInf());
        }
        return super.getBeansTargetFolder(create);
    }

}
