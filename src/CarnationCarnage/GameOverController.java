/**
 * Author: Isaac McClure
 * Date: Jul 8, 2018
 */
package CarnationCarnage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
        loader.setLocation(CarnationCarnageMain.class.getResource(
                "/CarnationCarnage/FXMLFiles/MainMenu.fxml"));
        BorderPane mainMenu = loader.load();

        // show scene
        Stage primaryStage = (Stage) gameOverPane.getScene().getWindow();
        primaryStage.setScene(new Scene(mainMenu));
    }

    public void reInitialize(Player winner) {
        // set up the winner labels
        titleLabel.setText(winner.getName() + " Wins!!!");
        winnerLabel.setText(winner.getName() + ":");
        winnerScoreLabel.setText(String.valueOf(winner.getScore()));
        winnerHitRateLabel.setText(String.valueOf(winner.getHits() / winner.getTotalShots()));
        
        // set up the loser labels
        Player loser = winner.getOpponent();
        loserLabel.setText(loser.getName() + ":");
        loserScoreLabel.setText(String.valueOf(loser.getScore()));
        try {
        // if the winner wins in one turn it can cause division by 0
            loserHitRateLabel.setText(String.valueOf(loser.getHits() / loser.getTotalShots()));
        }
        catch(ArithmeticException e) {
            loserHitRateLabel.setText("0");
        }
    }

}