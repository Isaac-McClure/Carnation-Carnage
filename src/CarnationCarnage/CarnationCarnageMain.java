/**
 * Author: Isaac McClure
 * Date: Jul 3, 2018
 */
package CarnationCarnage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.applet.Main;

//Class initializes Java fx
public class CarnationCarnageMain extends Application{
    private MainMenuController mainMenu;
    private Stage primaryStage;
    private BorderPane startScreen;
    private Scene startScene;
    private FXMLLoader loader = new FXMLLoader();

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //initialize stage
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Carnation Carnage");      
        showMainView();
        
    }
    
    private void showMainView() throws IOException {
        //Load in the scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/startScreen.fxml"));       
        startScreen = loader.load();
        startScene = new Scene(startScreen);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
       
  
    public static void main(String args[]) {
        launch(args);
    }
}
