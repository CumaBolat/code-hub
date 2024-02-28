package tr.mu.posta.cuma.projects.gol.components;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class GameOfLifePatternLoader {

  private Map<String, int[][]> patternGrids = new ConcurrentHashMap<>();

  public GameOfLifePatternLoader() {
    
  }

  public int[][] getPatternGrid(String patternName) {
    if (!this.patternGrids.containsKey(patternName)) return new int[100][100];

    return this.patternGrids.get(patternName);
  }
}
