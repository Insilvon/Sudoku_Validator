import java.io.File;
import java.util.Scanner;

/**
 * Class which validates a sudoku grid. Uses three threads to watch the columns, the rows, and the blocks.
 */
public class main {
    // Boolean variables which represent each check. A "false" means that an error in that column was found.
    private static boolean cols = false;
    private static boolean rows = false;
    private static boolean blocks = false;
    // A custom ADT structure for Puzzles.
    private static Puzzle myPuzzle;

    /**
     * Core method which instantiates the threads and initializes the sudoku puzzle.
     * @param file The user selected file containing the data to be read.
     * @return A string to log to a GUI - either "This was valid!" or "This wasn't valid!"
     */
    public static String heartbeat(File file) {
        myPuzzle = new Puzzle(file);

        Thread t1 = new Thread(new Columns());
        Thread t2 = new Thread(new Rows());
        Thread t3 = new Thread(new Blocks());

        t1.run();
        t2.run();
        t3.run();

        if (cols && rows && blocks) {
            // Reset the variables so that the next time the program is run,
            // it won't affect it.
            rows = false;
            cols = false;
            blocks = false;
            return "\n\nThis is a valid puzzle!";
        }
        else {
            // Reset the variables so that the next time the program is run,
            // it won't affect it.
            rows = false;
            cols = false;
            blocks = false;
            return "\n\nThis is not a valid puzzle!";
        }
    }

    /**
     * Class which checks all the columns in the sudoku puzzle.
     */
    public static class Columns implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getCol(i).clone())) {
                    return;
                }
            }
            cols = true;
        }

    }

    /**
     * Class which checks all the rows in the sudoku puzzle.
     */
    public static class Rows implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getRow(i).clone())) {
                    return;
                }
            }
            rows = true;
        }
    }

    /**
     * Class which checks all the blocks in the sudoku puzzle.
     */
    public static class Blocks implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getBlock(i).clone())) {
                    return;
                }
            }
            blocks = true;
        }
    }
}