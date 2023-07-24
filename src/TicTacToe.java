import java.util.Scanner;
public class TicTacToe {
    // before main() declare the board array and the constants that define it
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];
    private static String currentPlayer = "X";

    //The helper methods will all go in the main file in the class and after the main() method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            clearBoard();
            display();

            while (true) {
                //get the coordinates for the move which should be 1 – 3 for the row and col
                //convert the player move coordinates to the array indices which are 0 – 2 by subtracting 1
                int rowMove, colMove;
                do {
                    rowMove = SafeInput.getRangedInt(in, "Enter row (1 - 3): ", 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(in, "Enter column (1 - 3): ", 1, 3) - 1;
                } while (!isValidMove(rowMove, colMove)); //not a valid move -> go back to run statement do again

                board[rowMove][colMove] = currentPlayer; //valid move, display row and col
                display();

                //appropriate check for a win or a tie
                //(i.e. if it is possible for a win or a tie at this point in the game, check for it.)
                //If there is a win or tie announce it and then prompt the players to play again.
                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X"; //Toggle the player (i.e. X becomes O, O becomes X)
            }

            if (!SafeInput.getYNConfirm(in, "Play again? (y/n): ")) {
                break;
            }
        }

        in.close();
    }
    private static void clearBoard() {
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            board[i][j] = " ";
        }
    } // sets all the board elements to a space
}

    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("| ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }//shows the Tic Tac Toe game used as part of the prompt for the users move choice…

    //returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            System.out.println("Invalid move. Row and column must be between 1 and 3.");
            return false;
        }
        if (!board[row][col].equals(" ")) {
            System.out.println("Cell is already taken. Choose another move.");
            return false;
        }
        return true;
    }

    //checks to see if there is a win state on the current board for the specified player (X or O)
    //This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    //checks for a row win for the specified player
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    //// checks for a col win for specified player
    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    //checks for a diagonal win for the specified player
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    // checks for a tie condition: all spaces on the board are filled OR there is an X and an O in every win vector
    // (i.e. all possible 8 wins are blocked by having both and X and an O in them.)
    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}