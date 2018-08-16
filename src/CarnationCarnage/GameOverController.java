/**
 * Author: Isaac McClure
 * Date: Jul 8, 2018
 */
package CarnationCarnage;

import java.io.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

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
	public Label titleLabel, winnerLabel, loserLabel, winnerScoreLabel, winnerHitRateLabel, loserScoreLabel,
			loserHitRateLabel;
	@FXML
	public Button continueButton;

	public String winnerName;
	public double winnerScore;
	private String[] highScoreNames = new String[6];
	private double[] highScores = new double[6];

	public void initialize() {
	}

	@FXML
	public void continueClicked() throws IOException {
		// Returns to main menu
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/MainMenu.fxml"));
		BorderPane mainMenu = loader.load();

		// show scene
		Stage primaryStage = (Stage) gameOverPane.getScene().getWindow();
		primaryStage.setScene(new Scene(mainMenu));
	}

	@FXML
	public void saveClicked() throws IOException {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			SaveFile(file);
		}
	}

	private void SaveFile(File file) {
		// TODO Auto-generated method stub

	}

	public void reInitialize(Player winner) throws IOException {
		// set up the winner labels
		titleLabel.setText(winner.getName() + " Wins!!!");
		winnerLabel.setText(winner.getName() + ":");
		winnerScoreLabel.setText(String.valueOf(winner.getScore()));
		try {
			// winning without firing a shot causes an arithmetic error, occurs
			// when other player
			// surrenders on first turn.
			winnerHitRateLabel.setText(String.valueOf(winner.getHits() / winner.getTotalShots()));
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
			loserHitRateLabel.setText(String.valueOf(loser.getHits() / loser.getTotalShots()));
		} catch (ArithmeticException e) {
			loserHitRateLabel.setText("0");
		}
		this.winnerName = winner.getName();
		this.winnerScore = winner.getScore();
		setHighScore();
	}

	public void setHighScore() throws IOException {
		// add new high score
		this.highScoreNames[5] = this.winnerName;
		this.highScores[5] = this.winnerScore;

		// load existing high scores
		FileReader inputReader = new FileReader("HighScore.txt");
		BufferedReader reader = new BufferedReader(inputReader);
		String line = null;
		int i = 0;

		while ((line = reader.readLine()) != null && i < 5) {
			System.out.println(line);

			this.highScoreNames[i] = line.substring(0, line.indexOf(","));
			this.highScores[i] = Double.parseDouble(line.substring(line.indexOf(",") + 1));
			System.out.println(highScoreNames[i]);
			System.out.println(highScores[i]);
			i++;
		}
		reader.close();

		System.out.println(this.highScoreNames[0] + " " + this.highScores[0]);
		System.out.println(this.highScoreNames[1] + " " + this.highScores[1]);
		System.out.println(this.highScoreNames[2] + " " + this.highScores[2]);
		System.out.println(this.highScoreNames[3] + " " + this.highScores[3]);
		System.out.println(this.highScoreNames[4] + " " + this.highScores[4]);
		System.out.println(this.highScoreNames[5] + " " + this.highScores[5]);

		// sort high scores
		int j = 0;
		double temp = 0;
		i=0;

		for (i = 0; i <= 4; i++) {
			for (j = 0; j <= 4; j++) {
				if (this.highScores[j + 1] > this.highScores[j]) {
					temp = this.highScores[j];
					this.highScores[j] = this.highScores[j + 1];
					this.highScores[j + 1] = temp;
				}
			}
		}

		// add top 5 into the text file to save
		FileWriter outputWriter = new FileWriter("HighScore.txt");
		BufferedWriter writer = new BufferedWriter(outputWriter);
		line = null;
		i = 0;
		while (i < 5) {
			outputWriter.write(this.highScoreNames[i] + "," + this.highScores[i] + "\n");
			i++;
		}
		outputWriter.close();
	}
}