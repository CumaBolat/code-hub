package tr.mu.posta.cuma.projects.ide.components;

import java.util.Arrays;

import tr.mu.posta.cuma.projects.ide.repos.CommandBuilder;

public class GeneralCommandBuilder implements CommandBuilder {

  private StringBuilder command = new StringBuilder();

  public String buildSecureCommand(String command) {
    command = command.trim();

    String[] commandParts = command.split("&&");

    for (int i = 0; i < commandParts.length; i++) {
      String commandPart = commandParts[i];
      String trimmedCommandPart = commandPart.trim().replace("\"", "");
      String[] commandPartParts = trimmedCommandPart.split(" ");
      String commandName = commandPartParts[0].trim();
      String[] commandArgs = commandPartParts.length > 1
          ? Arrays.copyOfRange(commandPartParts, 1, commandPartParts.length)
          : new String[0];

      if (this.unAllowedCommand(commandName)) {
        this.clearCommand();
        return commandName + ": command not allowed, please sign in or register!";
      }

      switch (commandName) {
        case "ls":
          this.ls(commandArgs);
          break;
        case "cd":
          this.cd(commandArgs);
          break;
        case "javac":
          this.javac(commandArgs);
          break;
        case "java":
          this.java(commandArgs);
          break;
        case "pwd":
          this.pwd();
          break;
        case " ":
          this.command.append(" ");
          break;
        default:
          return commandName + ": command not found!";
      }

      if (commandParts.length == 1 || i == commandParts.length - 1)
        continue;

      this.and();
    }

    String result = this.command.toString();
    this.clearCommand();

    return result;
  }

  @Override
  public void ls(String[] args) {
    this.command.append(" ls");

    this.addArgument(args);
  }

  @Override
  public void cd(String[] args) {
    this.command.append("cd");

    this.addArgument(args);
  }

  @Override
  public void javac(String[] args) {
    this.command.append(" javac");

    this.addArgument(args);
  }

  @Override
  public void java(String[] args) {
    this.command.append(" java");

    this.addArgument(args);
  }

  @Override
  public void pwd() {
    this.command.append(" pwd");
  }

  public void and() {
    this.command.append(" &&");
  }

  private boolean unAllowedCommand(String command) {
    return Arrays.asList(CommandBuilder.ALL_COMMANDS).contains(command)
        && !Arrays.asList(CommandBuilder.GUEST_USER_COMMANDS).contains(command);
  }

  private void clearCommand() {
    this.command = new StringBuilder();
  }

  private void addArgument(String[] arguments) {
    for (String argument : arguments) {
      this.command.append(" " + argument);
    }
  }
}
