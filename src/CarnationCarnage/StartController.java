package CarnationCarnage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartController {
    // This class just displays a start screen to look fancy
    @FXML
    private AnchorPane startPane;
    
    public void initialize() {
        
    }
    
    
    // launches main menu on button click
    @FXML
    private void launchMainMenu() throws IOException 
    {   
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/mainMenu.fxml"));        
        BorderPane menuScreen = loader.load();
        
        //Show scene
        Stage primaryStage = (Stage) startPane.getScene().getWindow();
        primaryStage.setScene(new Scene(menuScreen));
     }
    
}
