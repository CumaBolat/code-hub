package tr.mu.posta.cuma.ide.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.mu.posta.cuma.ide.components.Docker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class FileBrowserController {

  @Value("#{'${initial.code}'.split(',')}")
  private List<String> initialCode;

  @Autowired
  private Docker docker;
  
  @GetMapping("/getFilesList")
  public ResponseEntity<String[]> getFilesList() {
      try {
          String[] files = this.docker.executeTerminalCommand("ls").split("\n");
          return new ResponseEntity<>(files, HttpStatus.OK);
      } catch (Exception e) {
          e.printStackTrace();
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PostMapping("/openFile")
  public String openFile(@RequestBody String fileName) {
    try {
      String fileContent = this.docker.executeTerminalCommand("cat " + fileName);
      return fileContent;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @PostMapping("/createFile")
  public void createFile(@RequestBody String fileName) {
      try {
        String className = this.getClassName(fileName);
        this.docker.saveJavaCode(this.codeInitializer(className), className);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  private String codeInitializer(String className) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < this.initialCode.size(); i++) {
      if (this.initialCode.get(i).equals("Main")) {
        result.append(className);
        continue;
      }
      result.append(this.initialCode.get(i));
    }

    return result.toString();
  }

  private String getClassName(String fileName) {
    String[] splittedFileName = fileName.split("\\.");

    return splittedFileName[0].replace("\"", "");
  }
  
}
