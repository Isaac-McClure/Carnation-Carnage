package CarnationCarnage;

import java.io.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import CarnationCarnage.GameOverController;

public class LeaderBoardController extends Application {
	@FXML
	private AnchorPane Leader;
	private String highScoreNameList;
	private String highScoreList;
	@FXML
	private Label highScore;
	@FXML
	private Label highScoreName;

	public void initialize() throws IOException {
		loadHighScore();
	}

	public void loadHighScore() throws IOException {
		FileReader inputReader = new FileReader("HighScore.txt");
		BufferedReader reader = new BufferedReader(inputReader);
		String line = null;
		int i = 0;
		highScoreList = "";
		highScoreNameList = "";
		while ((line = reader.readLine()) != null && i < 5) {
			System.out.println(line);

			highScoreList += Double.parseDouble(line.substring(line.indexOf(",") + 1)) + "\n";
			highScoreNameList += line.substring(0, line.indexOf(",")) + "\n";
			i++;
		}
		reader.close();

		this.highScore.setText(highScoreList);
		this.highScoreName.setText(highScoreNameList);
	}

	@FXML
	public void loadClicked() throws IOException {

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
