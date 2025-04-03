
public class AIPlayer {
    private char symbol;
    private char opponentSymbol;

    public AIPlayer(char symbol, char opponentSymbol) {
        this.symbol = symbol;
        this.opponentSymbol = opponentSymbol;
    }

    public int[] findBestMove(Board board) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;
        char[][] grid = board.getGrid();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (grid[i][j] == Board.EMPTY) {
                    grid[i][j] = symbol;
                    int score = minimax(board, 3, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    grid[i][j] = Board.EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing, int alpha, int beta) {
        char[][] grid = board.getGrid();

        // Check for terminal states
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (grid[i][j] != Board.EMPTY) {
                    boolean isWin = board.checkWin(i, j, grid[i][j]);
                    if (isWin && grid[i][j] == symbol) return 10;
                    if (isWin && grid[i][j] == opponentSymbol) return -10;
                }
            }
        }

        if (board.isFull() || depth == 0) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (grid[i][j] == Board.EMPTY) {
                        grid[i][j] = symbol;
                        int score = minimax(board, depth - 1, false, alpha, beta);
                        grid[i][j] = Board.EMPTY;
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (grid[i][j] == Board.EMPTY) {
                        grid[i][j] = opponentSymbol;
                        int score = minimax(board, depth - 1, true, alpha, beta);
                        grid[i][j] = Board.EMPTY;
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        }
    }
}