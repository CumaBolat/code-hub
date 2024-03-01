package tr.mu.posta.cuma.projects.gol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import tr.mu.posta.cuma.projects.gol.components.GameOfLifePatternLoader;

@RestController
public class GameOfLifePatternController {

  @Autowired
  private GameOfLifePatternLoader patternLoader = new GameOfLifePatternLoader();

  @GetMapping("/pattern")
  public ResponseEntity<int[][]> getPatternGrid(@RequestHeader("simpSessionId") String sessionId, @RequestParam String pattern) {
    System.out.println("patternName= " + pattern);
    //this.printArray(this.patternLoader.getPatternGrid(" " + pattern));
    return ResponseEntity.ok(this.patternLoader.getPatternGrid(pattern));
  }

  private void printArray(int[][] ar) {
    for (int i = 0; i < ar.length; i++) {
      for (int j = 0; j < ar[i].length; j++) {
        System.out.print(ar[i][j]);
      }
      System.out.println();
    }
  }
}
