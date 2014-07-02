/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
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
package com.codenvy.ide.shell;

import com.codenvy.ide.BaseTest;

import org.junit.Test;

/**
 * @author Musienko Maxim
 *
 */
public class ShellHelpInfoTest extends BaseTest {

    private String HelpCommands =
            "  appcfg backend configure     Configure the specified backend.\n" +
            "  appcfg backend delete        Delete the specified backend.\n" +
            "  appcfg backend rollback      Roll back a previously in-progress update.\n" +
            "  appcfg backend set_state     Set start/stop state of the specified backend.\n" +
            "  appcfg backend update        Update the specified backend.\n" +
            "  appcfg backends              List all backends.\n" +
            "  appcfg backends rollback     Roll back all in-progress update.\n" +
            "  appcfg backends update       Update all backend.\n" +
            "  appcfg cron_info             Displays times for the next several runs of each cron job.\n" +
            "  appcfg request_logs          Write request logs in Apache common log format.\n" +
            "  appcfg resource_limits_info  Display resource limits.\n" +
            "  appcfg rollback              Rollback an in-progress update.\n" +
            "  appcfg update                Create or update an app version.\n" +
            "  appcfg update_cron           Update application cron jobs.\n" +
            "  appcfg update_dos            Update application DoS protection configuration.\n" +
            "  appcfg update_indexes        Update application indexes.\n" +
            "  appcfg update_queues         Update application task queue definitions.\n" +
            "  appcfg vacuum_indexes        Delete unused indexes from application.\n" +
            "  cat                          Concatenate files and print on the console.\n" +
            "  cd                           Changes the current folder.\n" +
            "  clear                        Clear the shell screen.\n" +
            "  git add                      Add file contents to the index.\n" +
            "  git commit                   Record changes to the repository.\n" +
            "  git init                     Initialize new GIT repository.\n" +
            "  git pull                     Fetch from and merge with another repository or a local branch.\n" +
            "  git push                     Update remote refs along with associated objects.\n" +
            "  git remote add               Adds a remote named  for the repository at .\n" +
            "  git remote list              Show a list of existing remotes.\n" +
            "  git remote rm                Remove the remote repository. All remote-tracking branches and " +
            "configuration settings for the remote are removed.\n" +
            "  git status                   Get status of working directory.\n" +
            "  help                         Type help to see this list.\n" +
            "  jobs                         List out the background jobs.\n" +
            "  kill                         Cancels the job.\n" +
            "  ls                           List information about the files and folders.\n" +
            "  mkdir                        Create new folder.\n" +
            "  mvn clean package            Start project build.\n" +
            "  pwd                          Print current folder path.\n" +
            "  rm                           Remove file or folder.\n" +
            "  vmc apps                     List deployed applications on Cloud Foundry\n" +
            "  vmc delete                   Delete the Cloud Foundry application\n" +
            "  vmc info                     Cloud Foundry system and account information\n" +
            "  vmc login                    Login to Cloud Foundry\n" +
            "  vmc restart                  Restart the Cloud Foundry application\n" +
            "  vmc set-target               Sets a new target\n" +
            "  vmc start                    Start the Cloud Foundry application\n" +
            "  vmc stats                    Display resource usage for the Cloud Foundry application\n" +
            "  vmc stop                     Stop the Cloud Foundry application\n" +
            "  vmc target                   Reports current target\n" +
            "  vmc targets                  List known targets and associated authorization tokens";

    @Test
    public void helpInfo() throws Exception {
        IDE.EXPLORER.waitOpened();
        IDE.SHELL.setIDEWindowHandle(driver.getWindowHandle());
        IDE.SHELL.callShellFromIde();
        IDE.SHELL.typeAndExecuteCommand("help");
        IDE.SHELL.getContent();
        IDE.SHELL.waitContainsTextInShell(HelpCommands);
        IDE.SHELL.typeAndExecuteCommand("clear");
        IDE.SHELL.waitNotContainsTextInShell(HelpCommands);
    }

}
