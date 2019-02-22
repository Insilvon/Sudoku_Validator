import java.io.File;
import java.util.Scanner;

public class main {
    private static boolean cols = false;
    private static boolean rows = false;
    static boolean blocks = false;
    static Puzzle myPuzzle;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        File file;

        while (true) {
            System.out.println("File name to look for?");
            String input = in.nextLine();
            String file_name = "./" + input;
            file = new File(file_name);
            if (file.exists()) break;
            else System.out.println("File does not exist. Try again?");
        }
        myPuzzle = new Puzzle(file);

        Thread t1 = new Thread(new Columns());
        Thread t2 = new Thread(new Rows());
        Thread t3 = new Thread(new Blocks());

        t1.run();
        t2.run();
        t3.run();

        if (cols && rows && blocks) System.out.println("Valid!");
        else System.out.println("Not valid!");
    }

    public static class Columns implements Runnable {
        @Override
        public void run() {
            System.out.println("Cols running!");
            for (int i = 0; i < 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getCol(i).clone())) {
                    return;
                }
            }
            cols = true;
            System.out.println("C done!");
        }

    }

    public static class Rows implements Runnable {
        @Override
        public void run() {
            System.out.println("Rows running!");
            for (int i = 0; i < 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getRow(i).clone())) {
                    return;
                }
            }
            rows = true;
            System.out.println("R done!");
        }
    }

    public static class Blocks implements Runnable {
        @Override
        public void run() {
            System.out.println("Blocks running!");
            for (int i = 1; i <= 9; i++) {
                if (!myPuzzle.validate(myPuzzle.getBlock(i).clone())) {
                    return;
                }
            }
            blocks = true;
            System.out.println("B done!");
        }
    }
}