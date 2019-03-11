import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Puzzle {

    private String[][] grid = new String[9][9];

    public Puzzle(File theFile) {
        try {
            Scanner reader = new Scanner(theFile);
            int row = 0;
            while(reader.hasNextLine()) {
                //get the line in the file
                StringBuilder line = new StringBuilder(reader.nextLine());
                //remove any decimals
                while (line.indexOf(".")!=-1) line.deleteCharAt(line.indexOf("."));
                String accepted = "123456789-";
                for (int i = 0; i<line.length(); i++){
                    if (!accepted.contains(line.charAt(i)+"")) line.deleteCharAt(i);
                }
                //convert to array
                String[] characters = line.toString().split("-");
                //slap it into the 2D array
                if (characters.length!=9) System.out.println("Invalid length!");
                else System.arraycopy(characters, 0, grid[row], 0, characters.length);
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
        for(int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                System.out.print(this.grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Fetches a row from the grid
     * @param index - the row number specified
     * @return new string[] of required numbers
     */
    public String[] getRow(int index){
        if (index<0 || index>8) {
            System.out.println("ERROR: INDEX OUT OF BOUNDS");
            return null;
        }
        return this.grid[index];
    }

    /**
     * Fetches a column from the grid
     * @param index - the column number to be retrieved
     * @return new string[] of required items
     */
    public String[] getCol(int index){
        if (index<0 || index>8) {
            System.out.println("ERROR: INDEX OUT OF BOUNDS");
            return null;
        }
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i<9;i++) temp.add(this.grid[i][index]);
        String[] temp2 = new String[temp.size()];
        for (int i = 0; i<temp.size(); i++) temp2[i] = temp.get(i);
        return temp2;
    }

    /**
     * Method which will fetch a block from the grid
     * @param non - the (not a quadrant) block to fetch, labeled t->b, l->r, 1-9
     * @return String[] of all values in the block
     */
    public String[] getBlock(int non){
        ArrayList<String> temp = new ArrayList<String>();
        int sRowIndex, eRowIndex, sColIndex, eColIndex;
        switch(non){
            case 1:
                eColIndex = eRowIndex = 3;
                sColIndex = sRowIndex = 0;
                break;
            case 2:
                sRowIndex = 0;
                eRowIndex = 3;
                sColIndex = 3;
                eColIndex = 6;
                break;
            case 3:
                sRowIndex = 0;
                eRowIndex = 3;
                sColIndex = 6;
                eColIndex = 9;
                break;
            case 4:
                sRowIndex = 3;
                eRowIndex = 6;
                sColIndex = 0;
                eColIndex = 3;
                break;
            case 5:
                sRowIndex = sColIndex = 3;
                eRowIndex = eColIndex = 6;
            case 6:
                sRowIndex = 3;
                eRowIndex = 6;
                sColIndex = 6;
                eColIndex = 9;
                break;
            case 7:
                sRowIndex = 6;
                eRowIndex = 9;
                sColIndex = 0;
                eColIndex = 3;
                break;
            case 8:
                sRowIndex = 6;
                eRowIndex = 9;
                sColIndex = 3;
                eColIndex = 6;
                break;
            case 9:
                sRowIndex = sColIndex = 6;
                eRowIndex = eColIndex = 9;
                break;
            default:
                System.out.println("ERROR: Requested grid out of bounds");
                return null;
        }
        for(int i = sRowIndex; i<eRowIndex; i++){
            for (int j = sColIndex; j<eColIndex; j++){
                temp.add(this.grid[i][j]);
            }
        }
        String[] temp2 = new String[temp.size()];
        for (int i = 0; i<temp.size(); i++) temp2[i] = temp.get(i);
        return temp2;
    }

    public boolean validate(String[] values){
        if (values.length!=9) return false;
        List answer = Arrays.asList("1","2","3","4","5","6","7","8","9");
        List temp = Arrays.asList(values);
        Collections.sort(temp);
        return temp.equals(answer);
    }
}
