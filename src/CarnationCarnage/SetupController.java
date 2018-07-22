package CarnationCarnage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SetupController {
    // This class displays a the setup screen
    @FXML
    private AnchorPane setupPane;
    @FXML
    Label currentPlayerLabel;
    private Player player;

    public void initialize() {
    }

    // Calls launch game if both players are done, and
    // Saves and resets the board if only one is.
    @FXML
    private void doneClicked() {

    }

    // Launches the inGameScene
    private void launchGame() throws IOException {
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class
                .getResource("/CarnationCarnage/FXMLFiles/inGameScene.fxml"));
        BorderPane playScreen = loader.load();
        
        // TODO Pass first player through to game scene
        
        
        // Show scene
        Stage primaryStage = (Stage) setupPane.getScene().getWindow();
        primaryStage.setScene(new Scene(playScreen));
    }

    public void setPlayer(Player inPlayer) {
        player = inPlayer;
    }

    public void reInitialise() {
        // TODO Auto-generated method stub
        currentPlayerLabel.setText(player.getName() + "'s Turn to Setup");
    }


}
