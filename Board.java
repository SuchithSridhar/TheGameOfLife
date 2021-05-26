import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Board.
 * This is a board for the game of life.
 * A 0 represents an empty cell and 1, a life.
 * @author Suchith Sridhar
 * Start Date: 24th May 2021
 */
public class Board {
    private int rows;
    private int columns;
    private int[][] board;

    /**
     * A constructor for the Board class.
     * @param rows The number of rows for the board.
     * @param columns The number of columns for the board.
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        board = new int[rows][columns];
    }

    public Board(String filename){
        Scanner in;
        Scanner lineParser;
        try {
            in = new Scanner(new File(filename));
        } catch(FileNotFoundException e){
            System.out.println("File was not found with that name.");
            in = null;
            System.exit(1);
        }

        boolean bool = false;

        while (in.hasNextLine()) {
            lineParser = new Scanner(in.nextLine());
            lineParser.useDelimiter(" ");
            if (!bool){
                this.rows = lineParser.nextInt();
                this.columns = lineParser.nextInt();
                this.board = new int[this.rows][this.columns];
                bool = true;
                continue;
            }
                
            board[ lineParser.nextInt() ][ lineParser.nextInt() ] = 1;
        }

    }

    public void seed(int[][] seedLocations) throws IndexOutOfBoundsException{
        for (int[] location : seedLocations) {
            if (location[0] < 0
                    || location[1] < 0
                    || location[0] > rows -1
                    || location[1] > columns -1
                    || location.length != 2
               ) {
                throw new IndexOutOfBoundsException("One of seed index out of bounds.");
            }

            board[location[0]][location[1]] = 1;
        }
    }

    public void step(){
       ArrayList<int[]> born = new ArrayList<int[]>();
       ArrayList<int[]> die = new ArrayList<int[]>();
       int liveAroundCell;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                liveAroundCell = 0;

                if ((i-1) >= 0 &&  (j-1) >= 0 && board[i-1][j-1] == 1){
                    liveAroundCell++;
                } 
                if ((j-1) >= 0 && board[i][j-1] == 1) {
                    liveAroundCell++;
                } 
                if ((i+1) < rows &&  (j-1) >= 0 && board[i+1][j-1] == 1) {
                    liveAroundCell++;
                } 
                if ((i-1) >= 0 && board[i-1][j] == 1) {
                    liveAroundCell++;
                } 
                if ((i+1) < rows && board[i+1][j] == 1) {
                    liveAroundCell++;
                } 
                if ((j+1) < columns && (i-1) >= 0 && board[i-1][j+1] == 1) {
                    liveAroundCell++;
                } 
                if ((j+1) < columns && board[i][j+1] == 1) {
                    liveAroundCell++;
                } 
                if ((j+1) < columns && (i+1) < rows && board[i+1][j+1] == 1) {
                    liveAroundCell++;
                }

                if (board[i][j] == 1 && ( liveAroundCell == 2 || liveAroundCell == 3 ))
                    continue;
                else if (board[i][j] == 0 && liveAroundCell == 3)
                    born.add(new int[]{i, j});
                else
                    die.add(new int[]{i,j});

            }
        }


        for (int[] item : die) {
            board[item[0]][item[1]] = 0;
        }
        for (int[] item : born) {
            board[item[0]][item[1]] = 1;
        }
    }

    public int[][] getBoard(){
        return this.board;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0)
                    str += ".";
                else
                    str += "#";
            }
            str += "\n";
        }
        return str;
    }
    

}
