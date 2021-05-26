package GameOfLife;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int[][] seeds;
        Board board;
        if (args.length == 0){
            board = new Board(4, 4);
            seeds = new int[][]{
                {2, 2},
                {1, 3},
                {2, 3},
                {0, 0}
            };
        } else {

            int[][] returned = readSeedsFromFileInput(args[0]);
            board = new Board(returned[0][0], returned[0][1]);
            seeds = new int[returned.length-1][2];
            for (int i = 1; i < returned.length; i++) {
               seeds[i-1] = returned[i];
            }
            
        }

        board.seed(seeds);

        Scanner in = new Scanner(System.in);
        while (true){
            clear();
            System.out.println(board);
            if ( in.nextLine().contains("end")) return;
            board.step();
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public static int[][] readSeedsFromFileInput(String filename) {
        Scanner in;
        Scanner lineParser;
        try {
            in = new Scanner(new File(filename));
        } catch(FileNotFoundException e){
            System.out.println("File was not found with that name.");
            in = null;
            System.exit(1);
        }

        ArrayList<int[]> seed = new ArrayList<int[]>();
        while (in.hasNextLine()) {
            lineParser = new Scanner(in.nextLine());
            lineParser.useDelimiter(" ");
            seed.add(new int[]{lineParser.nextInt(), lineParser.nextInt()});
        }

        int[][] seedArray = new int[seed.size()][2];
        return seed.toArray(seedArray);
    }
}
