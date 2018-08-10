package CarnationCarnage;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class LeaderBoardController extends Application {

    @FXML
    private AnchorPane Leader;

    public void initialize() {

    }

    @FXML
    public void loadClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
    }

    @FXML
    public void backClicked() throws IOException {
        // Send the user back to the main menu
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/mainMenu.fxml"));
        BorderPane menuScreen = loader.load();

        // Show Scene
        Stage primaryStage = (Stage) Leader.getScene().getWindow();
        primaryStage.setScene(new Scene(menuScreen));
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}
