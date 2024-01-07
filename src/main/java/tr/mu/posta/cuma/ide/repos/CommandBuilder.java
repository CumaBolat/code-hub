package tr.mu.posta.cuma.ide.repos;

public interface CommandBuilder {
  public final String[] ALL_COMMANDS = {
                                          "ls", "cd", "javac", "java", "pwd", "&&",
                                          "rm", "kill", "ps", "mkdir", "touch", "cat",
                                          "nano", "vim", "vi"
                                        };

  public final String[] GUEST_USER_COMMANDS = { "ls", "cd", "javac", "java", "pwd", "&&" };

  public void ls(String[] args);
  public void cd(String[] args);
  public void javac(String[] args);
  public void java(String[] args);
  public void pwd();
  public void and();
  public String buildSecureCommand(String command);
}
