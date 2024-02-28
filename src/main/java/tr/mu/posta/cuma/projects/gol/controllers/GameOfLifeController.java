package tr.mu.posta.cuma.projects.gol.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import tr.mu.posta.cuma.projects.gol.components.GameOfLifeGridTransformer;
import tr.mu.posta.cuma.projects.gol.components.GameOfLifePrefillGrid;

@Controller
public class GameOfLifeController {

  @Autowired
  private SimpMessagingTemplate template;

  private Map<String, Boolean> gameStates = new ConcurrentHashMap<>();
  private Map<String, Integer> gameSpeeds = new ConcurrentHashMap<>();

  private GameOfLifeGridTransformer transformer = new GameOfLifeGridTransformer();
  private GameOfLifePrefillGrid prefillGrid = new GameOfLifePrefillGrid();

  @MessageMapping("/gameoflife/start")
  @SendToUser("/gameoflife/output")
  public void startGame(SimpMessageHeaderAccessor headerAccessor, int[][] grid) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    this.gameStates.put(sessionId, true);

    int count = 0;
    while (this.gameStates.get(sessionId)) {
      try {
        Thread.sleep(this.gameSpeeds.getOrDefault(sessionId, 1000));
        grid = this.transformer.transformGrid(grid);
        this.template.convertAndSendToUser(sessionId, "/gameoflife/output", grid);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @MessageMapping("/gameoflife/stop")
  public void stopGame(SimpMessageHeaderAccessor headerAccessor) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");
    this.gameStates.put(sessionId, false);
  }

  @MessageMapping("/gameoflife/speed")
  public void changeSpeed(SimpMessageHeaderAccessor headerAccessor, int speed) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");
    this.gameSpeeds.put(sessionId, 1000 / speed);
  }

  @MessageMapping("/gameoflife/prefill")
  @SendToUser("/gameoflife/output")
  public void preFillGrid(SimpMessageHeaderAccessor headerAccessor, String pattern) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");
    int[][] grid;
    System.out.println(pattern);
    switch (pattern) {
      case "glider":
        System.out.println("inside Glide");
        grid = this.prefillGrid.glider();
        break;
      case "blinker":
        grid = this.prefillGrid.blinker();
        break;
      case "toad":
        grid = this.prefillGrid.toad();
        break;
      case "beacon":
        grid = this.prefillGrid.beacon();
        break;
      case "pulsar":
        grid = this.prefillGrid.pulsar();
        break;
      case "pentadecathlon":
        grid = this.prefillGrid.pentadecathlon();
        break;
      case "spaceship":
        grid = this.prefillGrid.spaceship();
        break;
      case "gosperglidergun":
        grid = this.prefillGrid.gosperGliderGun();
        break;
      case "achimsVariant":
        grid = this.prefillGrid.achimsVariant();
        break;
      default:
        grid = new int[100][100];
        break;
    }
    printArray(grid);
    this.template.convertAndSendToUser(sessionId, "/gameoflife/output", grid);

  }

  private void printArray(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
  }
}
