package tr.mu.posta.cuma.ide.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import tr.mu.posta.cuma.ide.models.Code;
import tr.mu.posta.cuma.ide.repos.CommandBuilder;
import tr.mu.posta.cuma.ide.components.Docker;
import tr.mu.posta.cuma.ide.components.GeneralCommandBuilder;

import java.util.Arrays;

@Controller
public class EditorController {

  private final CommandBuilder commandBuilder = new GeneralCommandBuilder();
  private long lastActivityTime = System.currentTimeMillis();

  @Value("${unallowed.command}")
  private String unallowedCommand;

  @Autowired
  private SimpMessagingTemplate template;

  @Autowired
  private Docker docker;

  @MessageMapping("/editor")
  @SendToUser("/editor/output")
  public String handleSubmit(SimpMessageHeaderAccessor headerAccessor, Code code) throws Exception {
    lastActivityTime = System.currentTimeMillis();
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    String className = this.getClassNameFromCode(code.getCode(), sessionId);
    String result = this.docker.executeJavaCode(code.getCode(), className, sessionId);

    this.template.convertAndSendToUser(sessionId, "/editor/output", result);
    return result;
  }

  @MessageMapping("/terminal")
  @SendToUser("/editor/output")
  public String handleTerminal(SimpMessageHeaderAccessor headerAccessor, String command) throws Exception {
    lastActivityTime = System.currentTimeMillis();

    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    String secureCommand = this.commandBuilder.buildSecureCommand(command);

    if (secureCommand.contains(this.unallowedCommand)) {
      this.template.convertAndSendToUser(sessionId, "/editor/output", this.unallowedCommand);
      return "Unallowed command";
    }

    String result = this.docker.executeTerminalCommand(secureCommand, sessionId);
    this.template.convertAndSendToUser(sessionId, "/editor/output", result);
    return result;
  }

  @MessageMapping("/save")
  @SendToUser("/editor/output")
  public void handleSave(SimpMessageHeaderAccessor headerAccessor, Code code) {
    lastActivityTime = System.currentTimeMillis();
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    String className = this.getClassNameFromCode(code.getCode(), sessionId);

    this.docker.saveJavaCode(code.getCode(), className, sessionId);
  }

  @PostMapping("/handleUserWorkspace")
  public ResponseEntity<Void> handleUserWorkspace(@RequestHeader("simpSessionId") String sessionId) {
    if (this.docker.userWorkspaceExist(sessionId)) {
      this.template.convertAndSendToUser(sessionId, "/editor/output", "workspace already exists");
      return ResponseEntity.ok().build();
    }

    this.lastActivityTime = System.currentTimeMillis();

    this.docker.createUserWorkspace(sessionId);
    this.template.convertAndSendToUser(sessionId, "/editor/output", "workspace created " + this.docker.getUserWorkspaceName());
    return ResponseEntity.ok().build();
  }

  @Scheduled(fixedRate = 60000)
  public void checkContainerActivity() {
    if (System.currentTimeMillis() - lastActivityTime > 2 * 60 * 1000) { // 2 minutes of inactivity
      this.docker.removeUserWorkspace();
      this.template.convertAndSendToUser(sessionId,"/editor/output", "Docker container stopped due to inactivity");
    }
  }

  private String getClassNameFromCode(String code, String sessionId) {
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
