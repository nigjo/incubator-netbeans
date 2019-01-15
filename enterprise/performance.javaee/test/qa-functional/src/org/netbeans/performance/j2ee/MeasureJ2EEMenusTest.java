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

package org.netbeans.performance.j2ee;

import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.modules.performance.utilities.PerformanceTestCase;
import org.netbeans.performance.j2ee.menus.AppServerPopupMenuTest;
import org.netbeans.performance.j2ee.menus.J2EEProjectsViewPopupMenuTest;
import org.netbeans.performance.j2ee.setup.J2EESetup;

/**
 * Measure UI-RESPONSIVENES and WINDOW_OPENING.
 *
 * @author  lmartinek@netbeans.org
 */
public class MeasureJ2EEMenusTest  {

 
    public static NbTestSuite suite() {
        PerformanceTestCase.prepareForMeasurements();

        NbTestSuite suite = new NbTestSuite("UI Responsiveness J2EE Menus suite");
        System.setProperty("suitename", MeasureJ2EEMenusTest.class.getCanonicalName());
        System.setProperty("suite", "UI Responsiveness J2EE Menus suite");

        suite.addTest(JellyTestCase.emptyConfiguration().reuseUserDir(true)
                .addTest(J2EESetup.class)
                .addTest(AppServerPopupMenuTest.class)
                .addTest(J2EEProjectsViewPopupMenuTest.class)
                .suite());
        return suite;
    }
}
