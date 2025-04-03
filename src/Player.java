import java.util.Scanner;
public class Player {
    private String name;
    private char symbol;
    private Scanner scanner;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    public int[] getMove(Board board) {
        while (true) {
            System.out.print(name + ", enter your move (row column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            scanner.nextLine();

            if (board.isValidMove(row, col)) {
                return new int[]{row, col};
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}