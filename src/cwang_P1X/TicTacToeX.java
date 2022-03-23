package cwang_P1X;
/**
 * The TicTacToeX class executes the main actions of the TicTacToeX game.
 */
public class TicTacToeX {

    private final int SIZE;                    // The size of the board
    private static String[][] twoArray;        // The 2D array
    private int xWin;                          // The times that X has wined
    private int oWin;                          // The times that O has wined
    private int tie;                           // The times in a tie

    /**
     * Constructor.
     *
     * @param size The size of the board.
     */
    public TicTacToeX(int size){

        SIZE = size;        // The size of the board

        // Assigned the new 2D array to twoArray
        twoArray = new String[SIZE][SIZE];

        // Call the the arrangeBoard
        arrangeBoard();
    }

    /**
     *  The ReSetTwoArray method reset the 2D array.
     */
    public void reSetTwoArray(){
        // Initialize the 2D array
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                twoArray[row][col] = "   |";
            }
        }
    }

    /**
     * The arrangeBoard method arranges the 2D array.
     */
    private void arrangeBoard(){

        // Arrange the 2D array
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++){
                twoArray[row][col] = "   |";
            }
        }
    }

    /**
     * The setTwoArray method determines whether the system should set the mark
     * of either X or O into the 2D array.
     *
     * @param player The mark of either X or O.
     * @param posR The position of row.
     * @param posC The position of column.
     * @return The result whether the mark is set into the 2D array.
     */
    public boolean setTwoArray(String player, int posR, int posC){

        // Set a mark into an element if an element is empty
        if (twoArray[posR][posC].equals("   |")){

            // Call placePiece method taking in player, posR, and posC
            placePiece(player, posR, posC);

            // Return the boolean
            return true;
        }
        else {
            // Return the boolean
            return false;
        }
    }

    /**
     * THe placePiece method sets the mark into the 2D array.
     *
     * @param player THe mark either X or O.
     * @param posR The position of row.
     * @param posC The position of col.
     */
    public void placePiece(String player, int posR, int posC){

        // Set the mark into an element of the 2D array
        twoArray[posR][posC] = " " + player + " |";
    }

    /**
     * The getBoard method displays the board.
     */
    public void getBoard(){

        System.out.println();

        // Display the fist number row of the board
        System.out.print(" ");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%3d ", i);
        }

        System.out.println();

        // Display the rest of the board
        for (int row = 0; row < SIZE; row++){
            System.out.printf("%2d", row);
            for (int col = 0; col < SIZE; col++) {
                System.out.print(twoArray[row][col]);
            }
            System.out.print("\n  ");
            System.out.print("----");
            for (int line = 1; line < SIZE; line++)
                System.out.print("----");
            System.out.println();
        }
    }

    /**
     * The getCheckWinner method determines whether there is a winner.
     *
     * @param player The mark of either X or O.
     * @return The result whether there is a winner.
     */
    public boolean getCheckWinner(String player){
        int index;                              // The loop control variable
        int rowDiagonals = SIZE - 1;            // The loop control variable for row of the front diagonals
        int colDiagonals = 0;                   // The loop control variable for column of the front diagonals
        int backSlash = 0;                      // The loop control variable for the back diagonals

        // Determine whether there is a line in each single row
        for (int row = 0; row < SIZE; row++){
            index = 0;     // Initialize index to 0

            // Determine each single row
            for (int col = 0; col < SIZE; col++){
                if (twoArray[row][col].equals(" " + player + " |")) {
                    index++;
                }
            }
            // Return true if there is a line
            if (index == SIZE) {
                if (player.equals("X"))
                    xWin++;
                else
                    oWin++;

                // Return true
                return true;
            }
        }

        // Determine whether there is a line in each single column
        for (int col = 0; col < SIZE; col++){
            index = 0;     // Initialize index to 0

            // Determine each single column
            for (int row = 0; row < SIZE; row++){
                if (twoArray[row][col].equals(" " + player + " |")) {
                    index++;    // Add one if an element is same to the mark
                }
            }
            // Return true if there is a line
            if (index == SIZE) {
                if (player.equals("X"))
                    xWin++;
                else
                    oWin++;

                // Return true
                return true;
            }
        }

        // Initialize the index to 0
        index = 0;

        // Determine whether the front line is in the same marks in a row
        while (rowDiagonals >= 0 && colDiagonals < SIZE){
            if (twoArray[rowDiagonals][colDiagonals].equals(" " + player + " |")) {
                index++;    // Add one if an element is same to the mark
            }
            rowDiagonals--;     // Minus one on row
            colDiagonals++;     // Add one on column
        }
        // Return true if there is a line
        if (index == SIZE) {
            if (player.equals("X"))
                xWin++;
            else
                oWin++;

            return true;
        }

        // Initialize the index to 0
        index = 0;

        // Determine Whether the back line is the mark in a row
        while (backSlash < SIZE){
            if (twoArray[backSlash][backSlash].equals(" " + player + " |")) {
                index++;    // Add one if an element is same to the mark
            }
            backSlash++;    // Add one on row and col
        }
        // Return true if there is a line
        if (index == SIZE) {
            if (player.equals("X"))
                xWin++;
            else
                oWin++;

            // Return true
            return true;
        }

        // Return false if there is no any line in the same marks in a row
        return false;
    }

    /**
     * The empty method displays the tie message, add one to tie, and return true.
     *
     * @return The result whether all elements are full
     */
    public boolean empty(){

        System.out.println("No winner - it was a tie!");

        // Add one time to tie
        tie++;

        // Return true
        return true;
    }

    /**
     * The getXWin method returns xWin.
     *
     * @return The boolean of true.
     */
    public int getXWin(){

        // Return the time that X has wined
        return xWin;
    }

    /**
     * The getOWinner method returns oWin.
     *
     * @return The times that O has wined.
     */
    public int getOWin(){

        // Return the times that O has wined
        return oWin;
    }

    /**
     * The getTie method returns tie.
     *
     * @return The times in a tie
     */
    public int getTie(){

        //Return the times in a tie
        return tie;
    }
}
