import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * GUI form which uses a filechooser to determine what file to open
 * For sudoku validation.
 */
public class FileChooserForm {
    private JPanel myPanel;
    private JButton button1;
    private JTextField textField1;
    private JTextArea messageLabel;
    private JTextArea colinCroweCOSC460TextArea;

    /**
     * Main method of the application. Creates the JFrame.
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("FileChooserForm");
        frame.setContentPane(new FileChooserForm().myPanel);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void readContents(File file){
        try {
            Scanner inp = new Scanner(file);
            String line = "";
            while (inp.hasNextLine()){
                line += inp.nextLine()+"\n";
            }

            String result = main.heartbeat(file);
            line+=result;
            messageLabel.setText(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private FileChooserForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("");
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(".../"));
                int returnValue = fc.showOpenDialog(myPanel);
                if (returnValue == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    String filename = file.getAbsolutePath();
                    messageLabel.setText(filename);
                    readContents(file);
                }
                else {
                    messageLabel.setText("No File Chosen");
                }
            }
        });
    }
}
