package CarnationCarnage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {

    private int score;
    private String highScore;
    private String name;

    Scanner kb1 = new Scanner(System.in);

    public String GetHighScore() {// or Int depending on what type is created in game class {

        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.dat"); // Or alternatively .txt file
            reader = new BufferedReader(readFile);
            return reader.readLine();

        } catch (Exception e) {
            return "0";
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //At the end of the match, call below method to save and load current game result and load previous game result
    public void checkScore() {
        if (highScore.equals("")) {
            return;
        }
        
        if (score > Integer.parseInt(highScore.split(":")[1])) {
            System.out.println("You set a new HighScore! Enter Your Name");
            name = kb1.nextLine();
            highScore = name + ":" + score;

            File scoreFile = new File("highScore.dat"); // Or alternatively .txt file
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter writeFile = null;
            BufferedWriter writer = null;
            try {
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore); //.write method will clearout and replace the previous method if higher score is created
            } catch (Exception e) {
                // Errors
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (Exception e) {
                    //Errors
                }
            }
        }
    }
}
