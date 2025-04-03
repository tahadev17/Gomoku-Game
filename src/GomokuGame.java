import java.util.Scanner;

public class GomokuGame {
    private Board board;
    private Scanner scanner;

    public GomokuGame() {
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Gomoku (Five in a Row)!");
        System.out.println("1. 1 Player (vs AI)");
        System.out.println("2. 2 Players");
        System.out.print("Select game mode (1 or 2): ");

        int mode = scanner.nextInt();
        scanner.nextLine();

        if (mode == 1) {
            playVsAI();
        } else if (mode == 2) {
            playTwoPlayers();
        } else {
            System.out.println("Invalid selection. Exiting.");
        }
    }

    private void playTwoPlayers() {
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name, 'B');
        Player player2 = new Player(player2Name, 'W');

        System.out.println(player1Name + " will be B (Black) and goes first.");
        System.out.println(player2Name + " will be W (White).");

        boolean player1Turn = true;
        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard();
            Player currentPlayer = player1Turn ? player1 : player2;

            int[] move = currentPlayer.getMove(board);
            board.makeMove(move[0], move[1], currentPlayer.getSymbol());

            if (board.checkWin(move[0], move[1], currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                gameOver = true;
            } else if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }

            player1Turn = !player1Turn;
        }
    }

    private void playVsAI() {
        System.out.print("Enter your name: ");
        String humanName = scanner.nextLine();

        System.out.print("Choose your symbol (B for Black, W for White): ");
        char humanSymbol = scanner.nextLine().toUpperCase().charAt(0);
        char aiSymbol = (humanSymbol == 'B') ? 'W' : 'B';

        Player humanPlayer = new Player(humanName, humanSymbol);
        AIPlayer aiPlayer = new AIPlayer(aiSymbol, humanSymbol);

        boolean humanTurn = (humanSymbol == 'B');
        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard();

            if (humanTurn) {
                System.out.println(humanName + "'s turn (" + humanSymbol + ")");
                int[] move = humanPlayer.getMove(board);
                board.makeMove(move[0], move[1], humanSymbol);

                if (board.checkWin(move[0], move[1], humanSymbol)) {
                    board.printBoard();
                    System.out.println(humanName + " wins!");
                    gameOver = true;
                }
            } else {
                System.out.println("AI's turn (" + aiSymbol + ")");
                int[] move = aiPlayer.findBestMove(board);
                board.makeMove(move[0], move[1], aiSymbol);
                System.out.println("AI plays at (" + move[0] + ", " + move[1] + ")");

                if (board.checkWin(move[0], move[1], aiSymbol)) {
                    board.printBoard();
                    System.out.println("AI wins!");
                    gameOver = true;
                }
            }

            if (!gameOver && board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }

            humanTurn = !humanTurn;
        }
    }

    public static void main(String[] args) {
        GomokuGame game = new GomokuGame();
        game.startGame();
    }
}