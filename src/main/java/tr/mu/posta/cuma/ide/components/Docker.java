package tr.mu.posta.cuma.ide.components;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Docker {

  private String containerName;
  private ShellCommandExecutor shellCommandExecutor = new ShellCommandExecutor();

  public Docker() {
    this.containerName = UUID.randomUUID().toString();
  }

  public void startContainer() {
    System.out.println("Starting container: " + this.containerName);
    String command = "docker run -itd --name " + this.containerName + " java-ide";
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public void removeContainer() {
    this.stopContainer();
    String command = "docker rm " + this.containerName;
    this.shellCommandExecutor.executeShellCommand(command);
  }

  private void stopContainer() {
    String command = "docker stop " + this.containerName;
    this.shellCommandExecutor.executeShellCommand(command);
  }

  public String executeJavaCode(String code, String className) {
    String fixedCode = this.removeQuotatotion(code);

    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \\ \"echo '" 
                            + fixedCode + "' > " + className + ".java && javac " + className
                            + ".java && java " + className + "\"";
    System.out.println("Docker command: " + dockerCommand);
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public void saveJavaCode(String code, String className) {
    String fixedCode = this.removeQuotatotion(code);

    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \\ \"echo '" 
                            + fixedCode + "' > " + className + ".java \"";
    
    System.out.println("Docker command: " + dockerCommand);
    this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public String executeTerminalCommand(String command) {
    String dockerCommand = "docker exec " + this.containerName + " /bin/sh -c \" " + command + "\"";
    System.out.println("Docker command: " + dockerCommand);
    return this.shellCommandExecutor.executeShellCommand(dockerCommand);
  }

  public boolean isContainerRunning() {
    String command = "docker container ls";
    String output = this.shellCommandExecutor.executeShellCommand(command);
    return output.contains(this.containerName);
  }

  public String getContainerName() {
    return this.containerName;
  }

  // I hope I spelled this right..
  private String removeQuotatotion(String code) {
    return code.replace("\"", "\\\"");
  }
}
