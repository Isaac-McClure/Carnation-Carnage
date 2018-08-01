package CarnationCarnage;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlayerCreationController {
    // This class just displays a start screen to look fancy
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtPlayerOneName;
    @FXML
    private TextField txtPlayerTwoName;
    @FXML 
    private Label helpLabel;
    
    private Player playerOne;
    private Player playerTwo;
 
    
    public void initialize() {
        
    }
    
    
    // launches main menu on button click
    @FXML
    private void launchSetupScreen(Player player) throws IOException 
    {   
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/SetupScreen.fxml"));        
        AnchorPane setupScreen = loader.load();
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        
        // Pass the first player to the SetupController
        SetupController controller = loader.<SetupController>getController();
        controller.setPlayer(player);
        controller.reInitialise();
        // Show scene 
        primaryStage.setScene(new Scene(setupScreen));
     }
    
    @FXML
    public void startButtonClicked() throws IOException {
        // Get names from text fields and validates them, then gets them created and starts game
        String playerOneName = txtPlayerOneName.getText();
        String playerTwoName = txtPlayerTwoName.getText();
        if(playerOneName.matches("^[A-za-z0-9_]+$") && playerTwoName.matches("^[A-za-z0-9_]+$")) {
            helpLabel.setText("Usernames are good");
            createPlayers(playerOneName, playerTwoName);
            coinFlip();
        }
        else {
            helpLabel.setText("Please enter valid usernames. Some symbols are dissallowed.");
        }
    }


    private void createPlayers(String playerOneName, String playerTwoName) {
        // Creates players and gives them references to one another
        playerOne = new Player(playerOneName);
        playerTwo = new Player(playerTwoName);
        
        playerOne.setOpponent(playerTwo);
        playerTwo.setOpponent(playerOne);
    }


    private void coinFlip() throws IOException {
        // Randomly decides who gets to go first, launches setup screen 
        int flip = ThreadLocalRandom.current().nextInt(1, 3);
        helpLabel.setText(String.valueOf(flip));
        try {
            if(flip == 1) {
                launchSetupScreen(playerOne);
            }
            else {
                launchSetupScreen(playerTwo);
            }
        }
        catch(IOException e) {
            helpLabel.setText("Error: Failed to open setup screen.");
            throw e;
        }
    }
    
}
