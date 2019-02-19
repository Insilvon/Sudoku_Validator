import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        File file;
        while (true) {
            System.out.println("File name to look for?");
            String input = in.nextLine();
            String file_name = "./"+input;
            file = new File(file_name);
            if (file.exists()) break;
            else System.out.println("File does not exist. Try again?");
        }
        Puzzle myPuzzle = new Puzzle(file);
        myPuzzle.printMatrix();
        System.out.println("================================");
        String[] temp = myPuzzle.getCol(0);
        for (int i = 0; i<temp.length; i++){
            System.out.print(temp[i]);
        }
        System.out.println();
        System.out.println("================================");
        temp = myPuzzle.getRow(0);
        for (int i = 0; i<temp.length; i++){
            System.out.print(temp[i]);
        }
        System.out.println();
        System.out.println("================================");
        temp = myPuzzle.getBlock(1);
        int check = 0;
        for (int i = 0; i<temp.length; i++){
            if (check == 3){
                check = 0;
                System.out.println();
            }
            System.out.print(temp[i]);
            check++;
        }
        System.out.println();
        System.out.println(myPuzzle.validate(temp));
    }
}
