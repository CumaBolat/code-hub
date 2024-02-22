package tr.mu.posta.cuma.projects.gol.components;

public class GameOfLifeGridTransformer {
  public int[][] transformGrid(int[][] grid) {
    int size = grid.length;
    int[][] newGrid = new int[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
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
}
