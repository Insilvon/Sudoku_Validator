import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle {

    public String[][] grid = new String[3][3];
    public Puzzle(File theFile) {

        try {
            Scanner reader = new Scanner(theFile);
            int row = 0;
            while(reader.hasNextLine()){
                //get the line in the file
                String line = reader.nextLine();
                //remove any decimals
                line.replace('.','');
                //split the line into modules by spaces
                String[] characters = line.split(" ");
                //check for line length and validate here
                if (characters.length!=9) {
                    System.out.println("Invalid length!");
                } else {
                    //loop through the split characters and add them to the grid
                    for (int i = 0; i< characters.length; i++){
                        grid[row][i] = characters[i];
                    }
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Prints out the contents of the matrix
     */
    public void printMatrix(){
        for(int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                System.out.print(this.grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
