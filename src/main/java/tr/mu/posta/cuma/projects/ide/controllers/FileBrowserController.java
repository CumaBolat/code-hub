package tr.mu.posta.cuma.projects.ide.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tr.mu.posta.cuma.projects.ide.components.Docker;

import java.util.List;

@RestController
public class FileBrowserController {

  @Value("#{'${initial.code}'.split(',')}")
  private List<String> initialCode;

  @Autowired
  private Docker docker;

  @GetMapping("/getFilesList")
  public ResponseEntity<String[]> getFilesList(@RequestHeader("simpSessionId") String sessionId) {
    try {
      String[] files = this.docker.executeTerminalCommand("ls", sessionId).split("\n");

      return ResponseEntity.ok(files);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/openFile")
  public ResponseEntity<String> openFile(@RequestBody String fileName,
      @RequestHeader("simpSessionId") String sessionId) {
    try {
      String fileContent = this.docker.executeTerminalCommand("cat " + fileName, sessionId);

      return ResponseEntity.ok(fileContent);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/createFile")
  public ResponseEntity<String> createFile(@RequestBody String fileName,
      @RequestHeader("simpSessionId") String sessionId) {
    try {
      String className = getClassName(fileName);
      String code = codeInitializer(className);

      docker.saveJavaCode(code, className, sessionId);

      return ResponseEntity.ok(code);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  private String codeInitializer(String className) {
    StringBuilder result = new StringBuilder();

    for (String codeLine : this.initialCode) {
      if (codeLine.equals("Main")) {
        result.append(className);
      } else {
        result.append(codeLine);
      }
    }

    return result.toString();
  }

  private String getClassName(String fileName) {
    String[] splittedFileName = fileName.split("\\.");

    return splittedFileName[0].replace("\"", "");
  }
}
