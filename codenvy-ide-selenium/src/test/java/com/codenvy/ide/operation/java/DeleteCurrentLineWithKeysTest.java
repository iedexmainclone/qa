/*
 *
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2013] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.ide.operation.java;

import com.codenvy.ide.VirtualFileSystemUtils;

import org.exoplatform.ide.vfs.shared.Link;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;

import java.util.Map;

public class DeleteCurrentLineWithKeysTest extends ServicesJavaTextFuction {
    private static final String PROJECT = DeleteCurrentLineWithKeysTest.class.getSimpleName();

    @BeforeClass
    public static void setUp() {
        final String filePath = "src/test/resources/org/exoplatform/ide/operation/java/calc.zip";

        try {
            Map<String, Link> project = VirtualFileSystemUtils.importZipProject(PROJECT, filePath);
        } catch (Exception e) {
        }
    }

    @AfterClass
    public static void tearDown() {
        try {
            VirtualFileSystemUtils.delete(PROJECT);
        } catch (Exception e) {
        }
    }

    @Test
    public void deleteCurrentLineWithKeys() throws Exception {
        IDE.EXPLORER.waitOpened();
        IDE.OPEN.openProject(PROJECT);
        IDE.PACKAGE_EXPLORER.waitPackageExplorerOpened();
        IDE.PACKAGE_EXPLORER.waitItemInPackageExplorer(PROJECT);
        openSpringJavaTetsFile();

        IDE.GOTOLINE.goToLine(1);
        IDE.STATUSBAR.waitCursorPositionAt("1 : 1");
        IDE.JAVAEDITOR.typeTextIntoJavaEditor(Keys.CONTROL.toString() + "d");
        IDE.JAVAEDITOR.waitWhileJavaEditorWillNotContainSpecifiedText("package sumcontroller;");
    }
}
