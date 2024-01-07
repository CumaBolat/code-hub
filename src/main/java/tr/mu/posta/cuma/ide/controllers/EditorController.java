package tr.mu.posta.cuma.ide.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import tr.mu.posta.cuma.ide.models.Code;
import tr.mu.posta.cuma.ide.repos.CommandBuilder;
import tr.mu.posta.cuma.ide.components.Docker;
import tr.mu.posta.cuma.ide.components.DockerSingleton;
import tr.mu.posta.cuma.ide.components.GeneralCommandBuilder;

@Controller
public class EditorController {

  private final Docker docker = DockerSingleton.getInstance();
  private final CommandBuilder commandBuilder = new GeneralCommandBuilder();
  private long lastActivityTime = System.currentTimeMillis();

  @Value("${unallowed.command}")
  private String unallowedCommand;

  @Autowired
  private SimpMessagingTemplate template;

  @MessageMapping("/editor")
  @SendTo("/editor/output")
  public String handleSubmit(Code code) throws Exception {
    lastActivityTime = System.currentTimeMillis(); // Update last activity time
    String className = this.getClassNameFromCode(code.getCode());
    System.out.println("Class name: " + className);
    System.out.println("Code: " + code.getCode());
    
    return this.docker.executeJavaCode(code.getCode(), className);
  }

  @MessageMapping("/terminal")
  @SendTo("/editor/output")
  public String handleTerminal(String command) throws Exception {
    lastActivityTime = System.currentTimeMillis();

    String secureCommand = this.commandBuilder.buildSecureCommand(command);
    System.out.println("new, secure command: " + secureCommand);
    if (secureCommand.contains(this.unallowedCommand)) return secureCommand;

    return this.docker.executeTerminalCommand(secureCommand);
  }

  @MessageMapping("/save")
  public void handleSave(Code code) {
    lastActivityTime = System.currentTimeMillis();
    String className = this.getClassNameFromCode(code.getCode());

    this.docker.saveJavaCode(code.getCode(), className);
  }

  @PostMapping("/handleDockerContainer")
  public ResponseEntity<Void> handleCreateDockerContainer() {
    if (this.docker.isContainerRunning()) {
      this.template.convertAndSend("/editor/output", "Docker container already running");
      return ResponseEntity.ok().build();
    }

    this.docker.startContainer();
    this.template.convertAndSend("/editor/output", "Docker container started " + this.docker.getContainerName());
    return ResponseEntity.ok().build();
  }  

  @Scheduled(fixedRate = 60000)
  public void checkContainerActivity() {
    if (!this.docker.isContainerRunning()) return;

    if (System.currentTimeMillis() - lastActivityTime > 2 * 60 * 1000) { // 2 minutes of inactivity
      this.docker.removeContainer();
      this.template.convertAndSend("/editor/output", "Docker container stopped due to inactivity");
    }
  }

  private String getClassNameFromCode(String code) {
    String className = "no-class-name";
    String[] lines = code.split("\n");

    for (String line : lines) {
      if (line.contains("class")) {
        String[] words = line.trim().split(" ");
        int classIndex = Arrays.asList(words).indexOf("class");
        if (classIndex != -1 && classIndex + 1 < words.length) {
          className = words[classIndex + 1].replaceAll("\\{", ""); // Remove { if exists
        }
        break;
      }
    }

    return className;
  }
}
