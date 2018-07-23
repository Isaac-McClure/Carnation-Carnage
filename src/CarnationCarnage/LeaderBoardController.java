package CarnationCarnage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


    public class LeaderBoardController extends Application{

        @FXML
        private AnchorPane ScoreBoard;
        public void initialize(){
            
        }
           
        @FXML
        public void backClicked() throws IOException {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/mainMenu.fxml"));        
            BorderPane menuScreen = loader.load();
            
            // Show Scene
            Stage primaryStage = (Stage) ScoreBoard.getScene().getWindow();
            primaryStage.setScene(new Scene(menuScreen));
         }



        @Override
        public void start(Stage arg0) throws Exception {
            // TODO Auto-generated method stub
            
        }
    }
    