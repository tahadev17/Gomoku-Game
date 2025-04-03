public class Board {
    public static final int SIZE = 9;
    public static final char EMPTY = '.';
    private char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == EMPTY;
    }

    public void makeMove(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean checkWin(int row, int col, char symbol) {
        return checkDirection(row, col, 1, 0, symbol) || // horizontal
                checkDirection(row, col, 0, 1, symbol) || // vertical
                checkDirection(row, col, 1, 1, symbol) || // diagonal \
                checkDirection(row, col, 1, -1, symbol);  // diagonal /
    }

    private boolean checkDirection(int row, int col, int rowDir, int colDir, char symbol) {
        int count = 1;
        count += countConsecutive(row, col, rowDir, colDir, symbol);
        count += countConsecutive(row, col, -rowDir, -colDir, symbol);
        return count >= 5;
    }

    private int countConsecutive(int row, int col, int rowDir, int colDir, char symbol) {
        int count = 0;
        int r = row + rowDir;
        int c = col + colDir;

        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == symbol) {
            count++;
            r += rowDir;
            c += colDir;
        }
        return count;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
}