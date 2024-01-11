package tr.mu.posta.cuma.ide.components;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Docker {
  private String userWorkSpaceName;
  private ShellCommandExecutor shellCommandExecutor = new ShellCommandExecutor();

  public Docker() {
    this.userWorkSpaceName = UUID.randomUUID().toString();
  }

  public void createUserWorkspace() {
    String command = "mkdir " + this.userWorkSpaceName;
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public void removeUserWorkspace() {
    String command = "rm -rf " + this.userWorkSpaceName;
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public String executeJavaCode(String code, String className) {
    String dockerCommand = this.enterUserWorkspace() + " echo '" 
                            + code + "' > " + className + ".java && javac " + className
                            + ".java && java " + className;
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public void saveJavaCode(String code, String className) {
    String dockerCommand = this.enterUserWorkspace() + " echo '" 
                            + code + "' > " + className + ".java";
    
    this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public String executeTerminalCommand(String command) {
    String dockerCommand = this.enterUserWorkspace() + command;
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public boolean userWorkspaceExist() {
    String command = "ls";
    String output = this.shellCommandExecutor.executeShellCommand(command);
    return output.contains(this.userWorkSpaceName);
  }

  public String getUserWorkspaceName() {
    return this.userWorkSpaceName;
  }

  private String enterUserWorkspace() {
    return "cd " + this.userWorkSpaceName + " &&";
  }

  String deleteUserWorkSpace() {
    String command = "rm -rf " + this.userWorkSpaceName + "\"";
    return this.shellCommandExecutor.executeShellCommand(command);
  }
}
