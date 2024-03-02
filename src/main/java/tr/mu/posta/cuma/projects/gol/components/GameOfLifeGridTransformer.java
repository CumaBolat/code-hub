package tr.mu.posta.cuma.projects.gol.components;

public class GameOfLifeGridTransformer {

  public int[][] addInvisiblePadding(int[][] grid, int gridSize) {
    int[][] newGrid = new int[gridSize][gridSize];

    int sidePadding = (gridSize - grid.length) / 2; // Empty space between one side of the grid and the edge of the canvas

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        newGrid[i + sidePadding][j + sidePadding] = grid[i][j];
      }
    }

    return newGrid;
  }

  public int[][] transformGrid(int[][] grid) {
    int[][] newGrid = new int[grid.length][grid.length];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
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

  public int[][] chopExtraPadding(int[][] grid, int chopSize) {
    int size = grid.length - 2 * chopSize;
    int[][] choppedgrid = new int[size][size];

    for (int i = chopSize; i < grid.length - chopSize; i++) {
      for (int j = chopSize; j < grid[i].length - chopSize; j++) {
        choppedgrid[i - chopSize][j - chopSize] = grid[i][j];
      }
    }

    return choppedgrid;
  }
}
