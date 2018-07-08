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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//Controller class for MainMenu.FXML 
public class HowToController extends Application{
    // This class shows and handles input from the main menu
    @FXML
    private AnchorPane howToPane;
    public void initialize(){
        
    }
       
    
    @FXML
    public void backClicked() throws IOException {
        // Send the user back to the main menu
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/mainMenu.fxml"));        
        BorderPane menuScreen = loader.load();
        
        // Show Scene
        Stage primaryStage = (Stage) howToPane.getScene().getWindow();
        primaryStage.setScene(new Scene(menuScreen));
     }
    


    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

}