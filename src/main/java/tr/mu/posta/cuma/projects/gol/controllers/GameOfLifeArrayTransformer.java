package tr.mu.posta.cuma.projects.gol.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GameOfLifeArrayTransformer {

  @Autowired
  private SimpMessagingTemplate template;

  private Map<String, Boolean> gameStates = new ConcurrentHashMap<>();
  private Map<String, Integer> gameSpeeds = new ConcurrentHashMap<>();
  
  @MessageMapping("/gameoflife/start")
  @SendToUser("/gameoflife/output")
  public void startGame(SimpMessageHeaderAccessor headerAccessor, int[][] grid) {
    String sessionId = (String) headerAccessor.getSessionAttributes().get("httpSessionId");

    //if (this.gameStates.getOrDefault(sessionId,false)) return;

    this.gameStates.put(sessionId, true);

    while (this.gameStates.get(sessionId)) {
      try {
        Thread.sleep(this.gameSpeeds.getOrDefault(sessionId, 1000));
        grid = this.transformArray(grid);
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
    this.gameSpeeds.put(sessionId, 1000/speed);
  }

  private int[][] transformArray(int[][] grid) {
    int[][] newGrid = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int count = 0;
        if (i > 0 && j > 0 && grid[i - 1][j - 1] == 1) {
          count++;
        }
        if (i > 0 && grid[i - 1][j] == 1) {
          count++;
        }
        if (i > 0 && j < grid[i].length - 1 && grid[i - 1][j + 1] == 1) {
          count++;
        }
        if (j > 0 && grid[i][j - 1] == 1) {
          count++;
        }
        if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
          count++;
        }
        if (i < grid.length - 1 && j > 0 && grid[i + 1][j - 1] == 1) {
          count++;
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
          count++;
        }
        if (i < grid.length - 1 && j < grid[i].length - 1 && grid[i + 1][j + 1] == 1) {
          count++;
        }
        if (grid[i][j] == 1) {
          if (count < 2 || count > 3) {
            newGrid[i][j] = 0;
          } else {
            newGrid[i][j] = 1;
          }
        } else {
          if (count == 3) {
            newGrid[i][j] = 1;
          } else {
            newGrid[i][j] = 0;
          }
        }
      }
    }
    return newGrid;
  }

  private void printgrid(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }

}
