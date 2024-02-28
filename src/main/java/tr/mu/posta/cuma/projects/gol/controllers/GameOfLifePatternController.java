package tr.mu.posta.cuma.projects.gol.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GameOfLifePatternController {

  @GetMapping("/pattern")
  public ResponseEntity<int[][]> getPatternGrid(@RequestHeader("simpSessionId") String sessionId, @RequestBody String pattern) {
    return ResponseEntity.ok(new int[100][100]);
  }

}
