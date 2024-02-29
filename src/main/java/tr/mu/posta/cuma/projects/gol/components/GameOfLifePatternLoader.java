package tr.mu.posta.cuma.projects.gol.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GameOfLifePatternLoader implements ApplicationRunner {

  private final String PATTERNS_PATH = "../patterns";

  private Map<String, int[][]> patternGrids = new ConcurrentHashMap<>();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // TODO Auto-generated method stub
    loadPatterns();
  }

  private void loadPatterns() {
    File patternsFolder = new File(PATTERNS_PATH);
    File[] patternFiles = patternsFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
    if (patternFiles != null) {
      for (File file : patternFiles) {
        String patternName = file.getName().replaceAll("\\.txt$", "");
        try {
          int[][] patternGrid = readPatternFromFile(file);
          patternGrids.put(patternName, patternGrid);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  private int[][] readPatternFromFile(File file) throws IOException {
    int length = 0;
    int gridSize = 0;

    BufferedReader counter = new BufferedReader(new FileReader(file));
    String line = counter.readLine();
    while (line != null) {
      length++;
      gridSize = Math.max(gridSize, line.length());
      line = counter.readLine();
    }

    counter.close();

    gridSize = Math.max(gridSize, length);

    int[][] patternGrid = new int[gridSize][gridSize];

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      /* TODO:
       * I need to implement the algorithm to read the pattern 
       * from file and put the pattern in a square grid.
       * 
       * I also need to center the pattern in the grid.
      */      
    } catch (IOException e) {
      e.printStackTrace();
    }

    return patternGrid;
  }

  public int[][] getPatternGrid(String patternName) {
    return this.patternGrids.getOrDefault(patternName, new int[100][100]);
  }
}
