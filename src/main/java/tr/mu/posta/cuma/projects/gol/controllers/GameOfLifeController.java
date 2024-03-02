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

@Controller
public class GameOfLifeController {

  @Autowired
  private SimpMessagingTemplate template;

  private final int INVISIBLE_PADDING_SIZE = 100;

  private Map<String, Boolean> gameStates = new ConcurrentHashMap<>();
  private Map<String, Integer> gameSpeeds = new ConcurrentHashMap<>();

  private GameOfLifeGridTransformer transformer = new GameOfLifeGridTransformer();

  @MessageMapping("/gameoflife/start")
  @SendToUser("/gameoflife/output")
  public void startGame(SimpMessageHeaderAccessor headerAccessor, int[][] grid) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    this.gameStates.put(sessionId, true);

    int gridSize = grid.length + this.INVISIBLE_PADDING_SIZE;
    grid = this.transformer.addInvisiblePadding(grid, gridSize);

    while (this.gameStates.get(sessionId)) {
      try {
        Thread.sleep(this.gameSpeeds.getOrDefault(sessionId, 1000));
        grid = this.transformer.transformGrid(grid);
        int[][] choppedgrid = this.transformer.chopExtraPadding(grid, this.INVISIBLE_PADDING_SIZE/2);
        this.template.convertAndSendToUser(sessionId, "/gameoflife/output", choppedgrid);
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

  private void printArray(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
  }
}
