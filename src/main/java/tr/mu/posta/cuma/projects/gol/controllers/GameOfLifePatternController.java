package tr.mu.posta.cuma.projects.gol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import tr.mu.posta.cuma.projects.gol.components.GameOfLifeDescriptionLoader;
import tr.mu.posta.cuma.projects.gol.components.GameOfLifePatternLoader;

@RestController
public class GameOfLifePatternController {

  @Autowired
  private GameOfLifePatternLoader patternLoader = new GameOfLifePatternLoader();

  @Autowired
  private GameOfLifeDescriptionLoader descriptionLoader = new GameOfLifeDescriptionLoader();

  @GetMapping("/pattern")
  public ResponseEntity<int[][]> getPatternGrid(@RequestHeader("simpSessionId") String sessionId, @RequestParam String pattern) {
    return ResponseEntity.ok(this.patternLoader.getPatternGrid(pattern));
  }

  @GetMapping("/pattern/description")
  public ResponseEntity<String> getPatternDescription(@RequestHeader("simpSessionId") String sessionId, @RequestParam String pattern) {
    return ResponseEntity.ok(this.descriptionLoader.getPatternDescription(pattern));
  }
}
