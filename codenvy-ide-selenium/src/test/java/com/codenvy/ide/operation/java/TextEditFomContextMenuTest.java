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

import com.codenvy.ide.MenuCommands;
import com.codenvy.ide.VirtualFileSystemUtils;

import org.exoplatform.ide.vfs.shared.Link;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class TextEditFomContextMenuTest extends ServicesJavaTextFuction {
    private static final String PROJECT = FormatJavaCodeFromEditMenuTest.class.getSimpleName();

    @BeforeClass
    public static void setUp() {
        final String filePath = "src/test/resources/org/exoplatform/ide/operation/java/FormatTextTest.zip";

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
    public void selectAllDleteUndoRedoTest() throws Exception {
        IDE.EXPLORER.waitOpened();
        IDE.OPEN.openProject(PROJECT);
        IDE.PACKAGE_EXPLORER.waitPackageExplorerOpened();
        IDE.PACKAGE_EXPLORER.waitItemInPackageExplorer(PROJECT);
        openJavaClassForFormat();
        IDE.JAVAEDITOR.callContextMenuIntoJavaEditor();
        IDE.CONTEXT_MENU.waitOpened();
        IDE.CONTEXT_MENU.runCommand(MenuCommands.Edit.SELECT_ALL);
        IDE.CONTEXT_MENU.waitClosed();

        IDE.JAVAEDITOR.callContextMenuIntoJavaEditor();
        IDE.CONTEXT_MENU.waitOpened();
        IDE.CONTEXT_MENU.runCommand(MenuCommands.Edit.DELETE);
        IDE.CONTEXT_MENU.waitClosed();

        assertTrue(IDE.JAVAEDITOR.getVisibleTextFromJavaEditor().isEmpty());

        IDE.JAVAEDITOR.callContextMenuIntoJavaEditor();
        IDE.CONTEXT_MENU.waitOpened();
        IDE.CONTEXT_MENU.runCommand(MenuCommands.Edit.UNDO_TYPING);
        IDE.CONTEXT_MENU.waitClosed();
        assertTrue(IDE.JAVAEDITOR.getVisibleTextFromJavaEditor().startsWith("package sumcontroller;"));
        assertTrue(IDE.JAVAEDITOR.getVisibleTextFromJavaEditor().contains("String one =\"\";"));

        IDE.JAVAEDITOR.callContextMenuIntoJavaEditor();
        IDE.CONTEXT_MENU.waitOpened();
        IDE.CONTEXT_MENU.runCommand(MenuCommands.Edit.REDO_TYPING);
        IDE.CONTEXT_MENU.waitClosed();
        assertTrue(IDE.JAVAEDITOR.getVisibleTextFromJavaEditor().isEmpty());

    }

}
