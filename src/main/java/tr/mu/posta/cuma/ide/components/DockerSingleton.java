package tr.mu.posta.cuma.ide.components;

public class DockerSingleton {
  private static Docker dockerInstance;

  private DockerSingleton() {}

  public static Docker getInstance() {
    if (dockerInstance == null) {
      dockerInstance = new Docker();
    }

    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        dockerInstance.removeContainer();
      }
    });

    return dockerInstance;
  }
}
