/**
 * Author: Isaac McClure
 * Date: Jul 8, 2018
 */
package CarnationCarnage;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

//Controller class for GameOverController.fxml
public class GameOverController {
    // This class shows and handles input from the main menu
    @FXML
    public AnchorPane gameOverPane = new AnchorPane();
    @FXML
    public Label titleLabel, winnerLabel, loserLabel, winnerScoreLabel,
            winnerHitRateLabel, loserScoreLabel, loserHitRateLabel;
    @FXML
    public Button continueButton;

    public void initialize() {
    }

    @FXML
    public void continueClicked() throws IOException {
        // Returns to main menu
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class
                .getResource("/CarnationCarnage/FXMLFiles/MainMenu.fxml"));
        BorderPane mainMenu = loader.load();

        // show scene
        Stage primaryStage = (Stage) gameOverPane.getScene().getWindow();
        primaryStage.setScene(new Scene(mainMenu));
    }
    
    @FXML
    public void saveClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            SaveFile(file);
        }
    }

    private void SaveFile(File file) {
        // TODO Auto-generated method stub
        
    }

    public void reInitialize(Player winner) {
        // set up the winner labels
        titleLabel.setText(winner.getName() + " Wins!!!");
        winnerLabel.setText(winner.getName() + ":");
        winnerScoreLabel.setText(String.valueOf(winner.getScore()));
        try {
            // winning without firing a shot causes an arithmetic error, occurs
            // when other player
            // surrenders on first turn.
            winnerHitRateLabel.setText(
                    String.valueOf(winner.getHits() / winner.getTotalShots()));
        }

        catch (ArithmeticException e) {
            winnerHitRateLabel.setText("0");
        }

        // set up the loser labels
        Player loser = winner.getOpponent();
        loserLabel.setText(loser.getName() + ":");
        loserScoreLabel.setText(String.valueOf(loser.getScore()));
        try {
            // if the winner wins in one turn it can cause division by 0
            loserHitRateLabel.setText(
                    String.valueOf(loser.getHits() / loser.getTotalShots()));
        } catch (ArithmeticException e) {
            loserHitRateLabel.setText("0");
        }
    }

}