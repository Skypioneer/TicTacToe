package cwang_P1X;
import java.util.Scanner;       // Needed for the Scanner object

/**
 * The TicTacToeX program allows the user to choose the size of the board, from the smallest (3 x 3)
 * to the largest (probably about 25 x 25, depending upon your display style - assume 80 column window width),
 * and repeat the game as many times as the user wants.
 * The game is for two players. One player places the X pieces and the other player places the O pieces, and
 * when one player covers an entire row, column or diagonal, that player wins the game.
 *
 * @author Jason Wang
 * @version 13.0
 */
public class P1X {
    /**
     * The main method calls four main methods to execute the TicTacToeX game.
     *
     * @param args A string array containing the command line arguments.
     */
    public static void main (String[] args){
        final int SIZE;     // The size of the board

        TicTacToeX board;   // Create the TicTacToeX object

        // Create a Scanner object
        Scanner keyboard = new Scanner(System.in);

        // Create the TicTacTie object named board taking in SIZE

        // Call the welcome method
        welcome();

        // Call sizeUser method taking in keyboard, and assign it to SIZE
        SIZE = sizeUser(keyboard);

        // Create a new TicTacToeX object taking in SIZE, and assign it to board
        board = new TicTacToeX(SIZE);

        // Call the mainLoop method taking in keyboard, board, and sizeUser
        mainLoop(keyboard, board, SIZE);

        // Call the goodbye method
        goodbye();

        // Close the Scanner object
        keyboard.close();
    }

    /**
     * The welcome message displays the welcome message.
     */
    public static void welcome(){
        System.out.println("Welcome to TicTacToeX!\n");
    }

    /**
     * The sizeUser method prompts for the size of the board.
     *
     * @param keyboard The Scanner object.
     * @return The size of the board.
     */
    public static int sizeUser(Scanner keyboard){
        int size;               // The size of the board
        final int THREE = 3;    // The lower board of the Tic Tac Toe size

        // Determine whether the user's number is valid
        do {
            // Prompt for the size of the board
            System.out.print("Enter the size that larger than 3, and it should be odd: ");
            size = keyboard.nextInt();

            // Display the message if the number is invalid
            if (size % 2 == 0 || size < THREE){
                System.out.println("invalid number. Try again!");
            }
        }
        while(size % 2 == 0 || size < THREE);

        // Return the size
        return size;
    }

    /**
     * The mainLoop method executes the rounds that two players, X and O, play until one player wins
     * or in a tie as many as they want.
     */
    public static void mainLoop(Scanner keyboard, TicTacToeX board, int size){
        final int ROUND = size * size;      // The number of the board's spaces
        final String X = "X";               // The X mark
        final String O = "O";               // The O mark
        int control = 0;                    // The loop control variable
        char ans;                           // The answer that whether the user wanna repeat
        boolean inputMark;                  // The variable that check whether the Mark is set
                                            // successful set into a space
        boolean winner;                     // The variable that declare there is a winner

        // Determine whether user wanna repeat
        do{
            // Determine whether there is a winner
            do {
                // Determine whether X is successfully set into an element of the 2D array
                do {
                    // Call the turn method taking in X, board, keyboard, and size, and assign it to inputMark
                    inputMark = Turn(X, board, keyboard, size);

                    // Call the checkWinner method taking in board, inputMark, and X, and assign it to winner
                    winner = checkWinner(board, inputMark, X);

                    // Call the winnerOut method taking in winner and X
                    winnerOut(winner, X);
                }
                while (!inputMark);

                // Add 2 to control
                control += 2;

                // Determine whether there is no winner and control that is less then Round
                if (!winner && control < ROUND){
                    // Determine whether O is successfully set into an element of the 2D array
                    do {
                        // Call the turn method taking in O, board, keyboard, and size, and assign it to inputMark
                        inputMark = Turn(O, board, keyboard, size);

                        // Call the checkWinner method taking in board, inputMark, and O, and assign it to winner
                        winner = checkWinner(board, inputMark, O);

                        // Call the winnerOut method taking in winner and O
                        winnerOut(winner, O);
                    }
                    while (!inputMark);
                }

                // Execute codes for a tie in the game
                if(!winner && control > ROUND) {
                    // Assign true to winner
                    winner = board.empty();
                }
            }
            while (!winner);

            // Initialize control to 0
            control = 0;

            // Call outCome method taking in X ,O, keyboard, and board, and
            ans = outCome(X, O, keyboard, board);
        }
        while (ans == 'y' || ans == 'Y');
    }

    /**
     * The winnerOut method announces who is winner.
     *
     * @param winner The variable behaved of whether there is a winner.
     * @param player X or O.
     */
    public static void winnerOut(boolean winner, String player){
        // Determine whether there is a winner
        if (winner)
            System.out.printf("%s, you win the game!\n", player);
    }

    /**
     * The checkWinner method checks whether there is a winner and return the result.
     *
     * @param board The 2D array TicTacToeX object.
     * @param inputMark The variable that check whether the Mark is set.
     * @param player X or O.
     * @return The result whether there is a winner
     */
    public static boolean checkWinner(TicTacToeX board, boolean inputMark, String player){
        boolean winnerOut = inputMark;

        // Determine whether there is a winner
        if (inputMark)
            winnerOut = board.getCheckWinner(player);

        // Return the result
        return winnerOut;
    }

    /**
     * The Turn method prompts for location and return the result.
     *
     * @param player X or O.
     * @param board The 2D array TicTacToeX object.
     * @param keyboard The Scanner object.
     * @param size The size of the board.
     * @return The result whether the user's input is valid
     */
    public static boolean Turn(String player, TicTacToeX board, Scanner keyboard, int size){
        int posR;             // The row position
        int posC;             // The col position
        boolean inputMark;    // The variable that check whether the Mark is set.

        // Display the Tic Tac Toe board
        board.getBoard();

        System.out.printf("%s, it is your turn.\n", player);

        System.out.print("Which row? ");
        posR = keyboard.nextInt();

        System.out.print("Which column? ");
        posC = keyboard.nextInt();

        // Determine whether the user's input is valid
        if (posR >= size || posC >= size || posR < 0 || posC < 0)
            inputMark = false;
        else
            inputMark = board.setTwoArray(player, posR, posC);

        // Prompt for invalid location
        if (!inputMark)
            System.out.println("Bad location, try again...");

        // Return whether the user's input is valid
        return inputMark;
    }

    /**
     * The outcome method displays the game's state in the end.
     *
     * @param X The X mark
     * @param O The O mark
     */
    public static char outCome(String X, String O, Scanner keyboard, TicTacToeX board){

        // Display the game state
        System.out.printf("\nGame State\n" +
                        "%s has won %d games.\n" +
                        "%s has won %d games.\n" +
                        "There have been %d tie games."
                        , X, board.getXWin(), O, board.getOWin(), board.getTie());

        // Return whether the user wanna repeat
        return repeat(keyboard, board);
    }

    /**
     * The repeat method asks whether user wanna repeat and return the decision.
     *
     * @param keyboard The Scanner object.
     * @param board The TicTacToeX object.
     * @return The user's answer.
     */
    public static char repeat(Scanner keyboard, TicTacToeX board){
        String repeat;  // The user's answer
        char ans;       // The first letter of the user's answer

        // Consume the rest of the line
        keyboard.nextLine();

        // Ask whether the user wanna play again
        System.out.print("\n\nDo you want to play again? ");
        repeat = keyboard.nextLine();
        ans = repeat.charAt(0);

        // Determine whether the 2D array elements
        if (ans == 'y' || ans == 'Y'){
            board.reSetTwoArray();
        }

        // Return the user's decision
        return ans;
    }

    /**
     * The goodbye method displays the goodbye message.
     */
    public static void goodbye(){
        System.out.println("\nThanks for playing Tic Tac Toe!");
    }
}
