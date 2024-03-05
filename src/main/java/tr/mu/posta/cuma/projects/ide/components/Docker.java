package tr.mu.posta.cuma.projects.ide.components;

import org.springframework.stereotype.Component;

@Component
public class Docker {

  private String userWorkSpaceName;
  private ShellCommandExecutor shellCommandExecutor = new ShellCommandExecutor();

  public void createUserWorkspace(String sessionId) {
    String command = "mkdir " + sessionId;

    this.userWorkSpaceName = sessionId;
    this.shellCommandExecutor.executeShellCommand(command, sessionId);
  }

  public void removeUserWorkspace(String sessionId) {
    String command = "rm -rf " + sessionId;

    this.shellCommandExecutor.executeShellCommand(command, this.userWorkSpaceName);
  }

  public String executeJavaCode(String code, String className, String sessionId) {
    String dockerCommand = this.enterUserWorkspace(sessionId) + " echo '"
        + code + "' > " + className + ".java && javac " + className
        + ".java && java " + className;

    return this.shellCommandExecutor.executeShellCommand(dockerCommand, sessionId);
  }

  public void saveJavaCode(String code, String className, String sessionId) {
    String dockerCommand = this.enterUserWorkspace(sessionId) + " echo '"
        + code + "' > " + className + ".java";

    this.shellCommandExecutor.executeShellCommand(dockerCommand, sessionId);
  }

  public String executeTerminalCommand(String command, String sessionId) {
    String dockerCommand = this.enterUserWorkspace(sessionId) + command;

    return this.shellCommandExecutor.executeShellCommand(dockerCommand, sessionId);
  }

  public boolean userWorkspaceExist(String sessionId) {
    String command = "ls";
    String output = this.shellCommandExecutor.executeShellCommand(command, sessionId);

    if (output == null) return false;
    
    return output.contains(sessionId);
  }

  public String getUserWorkspaceName() {
    return this.userWorkSpaceName;
  }

  private String enterUserWorkspace(String sessionId) {
    return "cd " + sessionId + " &&";
  }
}
