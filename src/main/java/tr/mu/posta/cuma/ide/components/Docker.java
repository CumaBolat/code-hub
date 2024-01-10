package tr.mu.posta.cuma.ide.components;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class Docker {
  private String userWorkSpaceName;
  private ShellCommandExecutor shellCommandExecutor = new ShellCommandExecutor();

  @Value("${container.name}")
  private String containerName;

  public Docker() {
    this.userWorkSpaceName = UUID.randomUUID().toString();
  }

  public void createUserWorkspace() {
    String command = "docker exec " + this.containerName + " /bin/sh -c \\ \"" + "mkdir " + this.userWorkSpaceName + "\"";
    System.out.println("Docker command: " + command);
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public void startContainer() {
    System.out.println("Starting container: " + this.containerName);
    String command = "docker run -itd --name " + this.containerName + " java-ide";
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public void removeUserWorkspace() {
    String command = "docker exec " + this.containerName + " /bin/sh -c \\ \"" + "rm -rf " + this.userWorkSpaceName + "\"";
    this.shellCommandExecutor.executeShellCommand(command);
  }

  private void stopContainer() {
    String command = "docker stop " + this.containerName;
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public String executeJavaCode(String code, String className) {
    String fixedCode = this.removeQuotation(code);

    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \\ \"" + this.enterUserWorkspace() + " echo '" 
                            + fixedCode + "' > " + className + ".java && javac " + className
                            + ".java && java " + className + "\"";
    System.out.println("Docker command: " + dockerCommand);
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public void saveJavaCode(String code, String className) {
    String fixedCode = this.removeQuotation(code);

    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \\ \"" + this.enterUserWorkspace() + " echo '" 
                            + fixedCode + "' > " + className + ".java \"";
    
    System.out.println("Docker command: " + dockerCommand);
    this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public String executeTerminalCommand(String command) {
    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \"" + this.enterUserWorkspace() + command + "\"";
    System.out.println("Docker command: " + dockerCommand);
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public boolean isContainerRunning() {
    String command = "docker container ls";
    String output = this.shellCommandExecutor.executeShellCommand(command);
    return output.contains(this.containerName);
  }

  public boolean doesUserWorkspaceExist() {
    String command = "docker exec " + this.containerName + " /bin/sh -c \\ \"ls\"";
    String output = this.shellCommandExecutor.executeShellCommand(command);
    return output.contains(this.userWorkSpaceName);
  }

  public String getContainerName() {
    return this.containerName;
  }

  public String getUserWorkspaceName() {
    return this.userWorkSpaceName;
  }

  private String removeQuotation(String code) {
    return code.replace("\"", "\\\"");
  }

  private String enterUserWorkspace() {
    return "cd " + this.userWorkSpaceName + " &&";
  }

  String deleteUserWorkSpace() {
    String command = "docker exec " + this.containerName + " /bin/sh -c \\ \"" + "rm -rf " + this.userWorkSpaceName + "\"";
    return this.shellCommandExecutor.executeShellCommand(command);
  }
}
