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
package org.netbeans.modules.javascript2.editor;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.modules.javascript2.editor.classpath.ClasspathProviderImplAccessor;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Petr Pisl
 */
public class JsCodeCompletionParamTypesTest extends JsCodeCompletionBase {
    
    public JsCodeCompletionParamTypesTest(String testName) {
        super(testName);
    }
    
    public void testParameterTypesDocDefinition() throws Exception {
        checkCompletion("testfiles/completion/paramTypes/paramTypes01.js", "param1.^length;", false);
    }
    
    public void testParameterTypesMethodDefinedInOtherFile01() throws Exception {
        checkCompletion("testfiles/completion/paramTypes/testFile.js", "MyParamTestContext.^definedInOtherFile(22);", false);
    }
    
    public void testShowMethodDefinedInOtherFile01() throws Exception {
        checkCompletion("testfiles/completion/paramTypes/paramTypes01.js", "MyParamTestContext.^testParamDoc();", false);
    }
    
    public void testShowMethodDefinedInOtherFile02() throws Exception {
        checkCompletion("testfiles/completion/paramTypes/testFile.js", "MyParamTestContext.^definedInOtherFile(22);", false);
    }
    
    public void testGlobalContext01() throws Exception {
        checkCompletion("testfiles/completion/paramTypes/testFile.js", "f^ormatter.print(\"text\");", false);
    }
    
    @Override
    protected Map<String, ClassPath> createClassPathsForTest() {
        List<FileObject> cpRoots = new LinkedList<FileObject>(ClasspathProviderImplAccessor.getJsStubs());
        cpRoots.add(FileUtil.toFileObject(new File(getDataDir(), "/testfiles/completion/paramTypes")));
        cpRoots.add(FileUtil.toFileObject(new File(getDataDir(), "/testfiles/completion/lib")));
        return Collections.singletonMap(
            JS_SOURCE_ID,
            ClassPathSupport.createClassPath(cpRoots.toArray(new FileObject[cpRoots.size()]))
        );
    }

    @Override
    protected boolean classPathContainsBinaries() {
        return true;
    }
}
