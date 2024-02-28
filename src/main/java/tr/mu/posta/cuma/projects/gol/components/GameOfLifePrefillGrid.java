package tr.mu.posta.cuma.projects.gol.components;

import org.springframework.stereotype.Component;

@Component
public class GameOfLifePrefillGrid {
  public int[][] blinker() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    // Horizontal blinker
    grid[mid][mid - 1] = 1;
    grid[mid][mid] = 1;
    grid[mid][mid + 1] = 1;
    // Vertical blinker
    // Uncomment if you want both horizontal and vertical blinkers simultaneously
    grid[mid - 1][mid] = 1;
    grid[mid][mid] = 1;
    grid[mid + 1][mid] = 1;
    return grid;
  }

  public int[][] glider() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    grid[mid][mid + 1] = 1;
    grid[mid + 1][mid + 2] = 1;
    grid[mid + 2][mid] = 1;
    grid[mid + 2][mid + 1] = 1;
    grid[mid + 2][mid + 2] = 1;
    System.out.println("Glider");
    printArray(grid);
    return grid;
  }

  public int[][] toad() {
    int[][] grid = new int[100][100];

    return grid;
  }

  public int[][] beacon() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    // First square
    grid[mid - 1][mid - 1] = 1;
    grid[mid - 1][mid] = 1;
    grid[mid][mid - 1] = 1;
    grid[mid][mid] = 1;
    // Second square
    grid[mid + 1][mid + 1] = 1;
    grid[mid + 1][mid + 2] = 1;
    grid[mid + 2][mid + 1] = 1;
    grid[mid + 2][mid + 2] = 1;
    return grid;
  }

  public int[][] pulsar() {
    int[][] grid = new int[100][100];
        int startRow = 40;
        int startCol = 40;
        // Set values according to the provided pattern
        grid[startRow + 3][startCol + 8] = grid[startRow + 3][startCol + 9] = grid[startRow + 3][startCol + 10] = grid[startRow + 3][startCol + 14] = grid[startRow + 3][startCol + 15] = grid[startRow + 3][startCol + 16] = 1;
        grid[startRow + 5][startCol + 6] = grid[startRow + 5][startCol + 11] = grid[startRow + 5][startCol + 13] = grid[startRow + 5][startCol + 18] = 1;
        grid[startRow + 6][startCol + 6] = grid[startRow + 6][startCol + 11] = grid[startRow + 6][startCol + 13] = grid[startRow + 6][startCol + 18] = 1;
        grid[startRow + 7][startCol + 6] = grid[startRow + 7][startCol + 11] = grid[startRow + 7][startCol + 13] = grid[startRow + 7][startCol + 18] = 1;
        grid[startRow + 8][startCol + 8] = grid[startRow + 8][startCol + 9] = grid[startRow + 8][startCol + 10] = grid[startRow + 8][startCol + 14] = grid[startRow + 8][startCol + 15] = grid[startRow + 8][startCol + 16] = 1;
        grid[startRow + 10][startCol + 8] = grid[startRow + 10][startCol + 9] = grid[startRow + 10][startCol + 10] = grid[startRow + 10][startCol + 14] = grid[startRow + 10][startCol + 15] = grid[startRow + 10][startCol + 16] = 1;
        grid[startRow + 11][startCol + 6] = grid[startRow + 11][startCol + 11] = grid[startRow + 11][startCol + 13] = grid[startRow + 11][startCol + 18] = 1;
        grid[startRow + 12][startCol + 6] = grid[startRow + 12][startCol + 11] = grid[startRow + 12][startCol + 13] = grid[startRow + 12][startCol + 18] = 1;
        grid[startRow + 13][startCol + 6] = grid[startRow + 13][startCol + 11] = grid[startRow + 13][startCol + 13] = grid[startRow + 13][startCol + 18] = 1;
        grid[startRow + 15][startCol + 8] = grid[startRow + 15][startCol + 9] = grid[startRow + 15][startCol + 10] = grid[startRow + 15][startCol + 14] = grid[startRow + 15][startCol + 15] = grid[startRow + 15][startCol + 16] = 1;
        return grid;
  }

  public int[][] pentadecathlon() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    // First row
    grid[mid][mid - 1] = 1;
    grid[mid][mid] = 1;
    grid[mid][mid + 1] = 1;
    // Second row
    grid[mid - 1][mid - 1] = 1;
    grid[mid - 1][mid + 1] = 1;
    // Third row
    grid[mid + 1][mid - 1] = 1;
    grid[mid + 1][mid + 1] = 1;
    // Fourth row
    grid[mid + 2][mid - 1] = 1;
    grid[mid + 2][mid] = 1;
    grid[mid + 2][mid + 1] = 1;
    return grid;
  }

  public int[][] spaceship() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    // Body
    grid[mid - 1][mid - 1] = 1;
    grid[mid - 1][mid + 1] = 1;
    grid[mid][mid - 2] = 1;
    grid[mid][mid - 1] = 1;
    grid[mid][mid] = 1;
    grid[mid][mid + 1] = 1;
    grid[mid][mid + 2] = 1;
    grid[mid + 1][mid - 2] = 1;
    grid[mid + 1][mid + 1] = 1;
    return grid;
  }

  public int[][] gosperGliderGun() {
    int[][] grid = new int[100][100];
    int mid = grid.length / 2;
    // Gosper Glider Gun pattern
    grid[mid - 2][mid + 34] = 1;
    grid[mid - 2][mid + 35] = 1;
    grid[mid - 1][mid + 34] = 1;
    grid[mid - 1][mid + 35] = 1;
    grid[mid][mid + 24] = 1;
    grid[mid][mid + 25] = 1;
    grid[mid][mid + 32] = 1;
    grid[mid][mid + 33] = 1;
    grid[mid][mid + 36] = 1;
    grid[mid][mid + 37] = 1;
    grid[mid + 1][mid + 22] = 1;
    grid[mid + 1][mid + 26] = 1;
    grid[mid + 1][mid + 32] = 1;
    grid[mid + 1][mid + 33] = 1;
    grid[mid + 1][mid + 36] = 1;
    grid[mid + 1][mid + 37] = 1;
    grid[mid + 2][mid + 12] = 1;
    grid[mid + 2][mid + 13] = 1;
    grid[mid + 2][mid + 22] = 1;
    grid[mid + 2][mid + 27] = 1;
    grid[mid + 2][mid + 28] = 1;
    grid[mid + 3][mid + 12] = 1;
    grid[mid + 3][mid + 13] = 1;
    grid[mid + 3][mid + 22] = 1;
    grid[mid + 3][mid + 26] = 1;
    grid[mid + 3][mid + 28] = 1;
    grid[mid + 3][mid + 29] = 1;
    grid[mid + 4][mid + 22] = 1;
    grid[mid + 4][mid + 27] = 1;
    grid[mid + 4][mid + 28] = 1;
    grid[mid + 5][mid + 23] = 1;
    grid[mid + 5][mid + 27] = 1;
    grid[mid + 5][mid + 28] = 1;
    grid[mid + 6][mid + 23] = 1;
    grid[mid + 6][mid + 27] = 1;
    grid[mid + 6][mid + 28] = 1;
    grid[mid + 7][mid + 24] = 1;
    grid[mid + 7][mid + 25] = 1;
    return grid;
  }

  public int[][] achimsVariant() {
    int[][] grid = new int[100][100];
    int midX = grid.length / 2;
    int midY = grid[0].length / 2;

    // Central oscillator
    grid[midX][midY - 1] = 1;
    grid[midX][midY] = 1;
    grid[midX][midY + 1] = 1;

    // Diagonal lines
    for (int i = -5; i <= 5; i++) {
      grid[midX - i][midY + i] = 1;
      grid[midX - i][midY - i] = 1;
    }

    // Vertical lines
    for (int i = -7; i <= 7; i++) {
      grid[midX + i][midY + 2] = 1;
      grid[midX + i][midY - 2] = 1;
    }

    // Horizontal lines
    for (int i = -7; i <= 7; i++) {
      grid[midX + 2][midY + i] = 1;
      grid[midX - 2][midY + i] = 1;
    }

    return grid;
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
