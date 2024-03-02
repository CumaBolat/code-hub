package tr.mu.posta.cuma.projects.gol.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GameOfLifeDescriptionLoader implements ApplicationRunner {
  
  private final String DESCRIPTON_PATH = "src/main/java/tr/mu/posta/cuma/projects/gol/patterns/descriptions";

  private Map<String, String> patternDescriptions = new ConcurrentHashMap<>();

  public String getPatternDescription(String patternName) {
    return this.patternDescriptions.getOrDefault(patternName, "No description available");
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("Loading descriptions...");
    loadDescriptions();
  }

  private void loadDescriptions() throws IOException {
    File descriptionsFolder = new File(this.DESCRIPTON_PATH);

    File[] descriptionFiles = descriptionsFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

    if (descriptionFiles != null) {
      for (File file : descriptionFiles) {
        String patternName = file.getName().replaceAll("\\.txt$", "");
        try {
          String description = readDescriptionFromFile(file);
          this.patternDescriptions.put(patternName, description);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  private String readDescriptionFromFile(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder description = new StringBuilder();
    String line = reader.readLine();
    while (line != null) {
      description.append(line);
      line = reader.readLine();
    }
    reader.close();
    return description.toString();
  }
}
