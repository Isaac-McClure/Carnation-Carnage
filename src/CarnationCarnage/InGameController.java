package CarnationCarnage;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import CarnationCarnage.Board;

//Crosshair image Created by Creaticca Creative Agency on Flaticon.com
public class InGameController {

	@FXML
	private AnchorPane inGamePane;
	@FXML
	private AnchorPane gridAnchorPane;
	@FXML
	private GridPane guessBoard;
	private Point guess;
	@FXML
	private GridPane playerBoard;
	@FXML
	private AnchorPane hitPane;
	@FXML
	Label currentPlayerLabel;
	@FXML
	Label playersLabel;
	@FXML
	Label scoreLabel;

	double roseMultiplier = 1.15;
	double carnationMultiplier = 1.12;
	double pansyMultiplier = 1.09;
	double tulipMultiplier = 1.06;
	double daisyMultiplier = 1.03;

	ImageView roseImage;
	ImageView carnationImage;
	ImageView pansyImage;
	ImageView tulipImage;
	ImageView daisyImage;

	// added by sonya
	ImageView roseAlive;
	ImageView carnationAlive;
	ImageView pansyAlive;
	ImageView tulipAlive;
	ImageView daisyAlive;

	int roseHit = 0;
	int carnationHit = 0;
	int pansyHit = 0;
	int tulipHit = 0;
	int daisyHit = 0;

	ImageView crosshair = null;

	private Player player;

	private boolean hidden = true;
	@FXML
	Label hiddenLabel;

	// TODO decide whether this is needed or not
	private Hashtable<ImageView, Flower> imageToFlower = new Hashtable<ImageView, Flower>();

	public void initialize() {
		crosshair = new ImageView("/CarnationCarnage/Images/crosshair.png");
		crosshair.setPreserveRatio(false);
		crosshair.setFitHeight(40);
		crosshair.setFitWidth(40);
		crosshair.setVisible(false);
		inGamePane.getChildren().add(crosshair);

	}

	// This method is called after initialize so we can pass in player first
	public void reInitialise() {
		// Set up graphics
		currentPlayerLabel.setText(player.getName() + "'s Turn to Play");
		// set up score for each player. Set to zero at beginning of game.
		scoreLabel.setText(player.getName() + "'s Score: " + String.format("%.0f", player.getScore()));

		// Set flowers in position with appropriate event handlers and
		// ImageViews
		roseImage = player.getBoard().getRose().getIcon();
		setFlowerInPosition(player.getBoard().getRose(), roseImage);
		inGamePane.getChildren().add(roseImage);
		// Add mapping to actual flower object, and to default position for
		// later
		imageToFlower.put(roseImage, player.getBoard().getRose());

		carnationImage = player.getBoard().getCarnation().getIcon();
		setFlowerInPosition(player.getBoard().getCarnation(), carnationImage);
		inGamePane.getChildren().add(carnationImage);
		// Add mapping to actual flower object, and to default position for
		// later
		imageToFlower.put(carnationImage, player.getBoard().getCarnation());

		pansyImage = player.getBoard().getPansy().getIcon();
		setFlowerInPosition(player.getBoard().getPansy(), pansyImage);
		inGamePane.getChildren().add(pansyImage);
		// Add mapping to actual flower object, and to default position for
		// later
		imageToFlower.put(pansyImage, player.getBoard().getPansy());

		tulipImage = player.getBoard().getTulip().getIcon();
		setFlowerInPosition(player.getBoard().getTulip(), tulipImage);
		inGamePane.getChildren().add(tulipImage);
		// Add mapping to actual flower object, and to default position for
		// later
		imageToFlower.put(tulipImage, player.getBoard().getTulip());

		daisyImage = player.getBoard().getDaisy().getIcon();
		setFlowerInPosition(player.getBoard().getDaisy(), daisyImage);
		inGamePane.getChildren().add(daisyImage);
		// Add mapping to actual flower object, and to default position for
		// later
		imageToFlower.put(daisyImage, player.getBoard().getDaisy());

		// Place hits and misses on both boards
		setHitsAndMisses(player.getBoard(), playerBoard);
		setHitsAndMisses(player.getOpponent().getBoard(), guessBoard);

		// hides flowers at the start of the turn
		for (Flower flower : player.getBoard().getPeicesRemaining()) {
			flower.getIcon().setVisible(false);
			hiddenLabel.setText("The peices are currently hidden");
		}
		pieceStatus();

	}

	private void pieceStatus() {
		// Sonya added this and my apologies for the crappy repetitive code. I just
		// wanted to get it working. I'll aim to clean it up

		if (player.getOpponent().getBoard().getRose().checkAlive() == true)
			roseAlive = new ImageView("/CarnationCarnage/Flowers/boardRose.png");
		else
			roseAlive = new ImageView("/CarnationCarnage/Flowers/boardRoseHit.png");
		roseAlive.setFitHeight(50);
		roseAlive.setFitWidth(250);
		roseAlive.setLayoutX(25);
		roseAlive.setLayoutY(16);
		hitPane.getChildren().add(roseAlive);

		if (player.getOpponent().getBoard().getCarnation().checkAlive() == true)
			carnationAlive = new ImageView("/CarnationCarnage/Flowers/boardCarnation.png");
		else
			carnationAlive = new ImageView("/CarnationCarnage/Flowers/boardCarnationHit.png");
		carnationAlive.setFitHeight(50);
		carnationAlive.setFitWidth(200);
		carnationAlive.setLayoutX(300);
		carnationAlive.setLayoutY(16);
		hitPane.getChildren().add(carnationAlive);

		if (player.getOpponent().getBoard().getPansy().checkAlive() == true)
			pansyAlive = new ImageView("/CarnationCarnage/Flowers/boardPansy.png");
		else
			pansyAlive = new ImageView("/CarnationCarnage/Flowers/boardPansyHit.png");
		pansyAlive.setFitHeight(50);
		pansyAlive.setFitWidth(150);
		pansyAlive.setLayoutX(520);
		pansyAlive.setLayoutY(19);
		hitPane.getChildren().add(pansyAlive);

		if (player.getOpponent().getBoard().getTulip().checkAlive() == true)
			tulipAlive = new ImageView("/CarnationCarnage/Flowers/boardTulip.png");
		else
			tulipAlive = new ImageView("/CarnationCarnage/Flowers/boardTulipHit.png");
		tulipAlive.setFitHeight(50);
		tulipAlive.setFitWidth(150);
		tulipAlive.setLayoutX(700);
		tulipAlive.setLayoutY(19);
		hitPane.getChildren().add(tulipAlive);

		if (player.getOpponent().getBoard().getDaisy().checkAlive() == true)
			daisyAlive = new ImageView("/CarnationCarnage/Flowers/boardDaisy.png");
		else
			daisyAlive = new ImageView("/CarnationCarnage/Flowers/boardDaisyHit.png");
		daisyAlive.setFitHeight(50);
		daisyAlive.setFitWidth(100);
		daisyAlive.setLayoutX(900);
		daisyAlive.setLayoutY(19);
		hitPane.getChildren().add(daisyAlive);
	}

	// Launches the inGameScene for next players turn
	private void launchGame() throws IOException {
		// Load Scene
		System.out.println("LAUNCH GAME");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/inGameScene.fxml"));
		AnchorPane playScreen = loader.load();

		// TODO Pass other player through to game scene
		InGameController controller = loader.<InGameController>getController();
		controller.setPlayer(player.getOpponent());
		controller.reInitialise();

		// Show scene
		Stage primaryStage = (Stage) inGamePane.getScene().getWindow();
		primaryStage.setScene(new Scene(playScreen));
	}

	public void setPlayer(Player inPlayer) {
		player = inPlayer;
	}

	// Places flowers based on their grid positions
	public void setFlowerInPosition(Flower flower, ImageView flowerImage) {

		ArrayList<Point> gridPositions = new ArrayList<>(flower.getPositions());
		gridPositions.addAll(flower.getDeadPositions());

		// Find top left position, even if it is dead
		Point topLeft = gridPositions.get(0);
		for (Point gridPosition : gridPositions) {
			// if the positions shares a column with topLeft but is higher
			// or shares a row with topLeft but is further left then make
			// topLeft equal it
			if ((gridPosition.x == topLeft.x && gridPosition.y < topLeft.y)
					|| (gridPosition.y == topLeft.y && gridPosition.x < topLeft.x)) {
				topLeft = gridPosition;
			}
		}

		// Translate to pixels
		double newLayoutX = (double) topLeft.x * 40;
		double newLayoutY = (double) topLeft.y * 40;

		// Make relative to grid
		newLayoutX += (gridAnchorPane.getLayoutX() + playerBoard.getLayoutX());
		newLayoutY += (gridAnchorPane.getLayoutY() + playerBoard.getLayoutY());

		// Assign position to flower image
		flowerImage.setLayoutX(newLayoutX);
		flowerImage.setLayoutY(newLayoutY);
	}

	// sets all pieces to invisible
	public void showHideBoardClicked() {
		hidden = !hidden;
		for (Flower flower : player.getBoard().getPeicesRemaining()) {
			flower.getIcon().setVisible(!flower.getIcon().isVisible());
			if (hidden) {
				hiddenLabel.setText("The peices are currently hidden");
			} else {
				hiddenLabel.setText("");
			}
		}
	}

	// Selects square for firing
	public void guessBoardClicked(MouseEvent event) {
		double eventX = event.getSceneX();
		double eventY = event.getSceneY();

		int eventGridX = (int) (eventX - (gridAnchorPane.getLayoutX() + guessBoard.getLayoutX())) / 40;
		int eventGridY = (int) (eventY - (gridAnchorPane.getLayoutY() + guessBoard.getLayoutY())) / 40;

		if (eventGridX >= 0 && eventGridX < 10 && eventGridY >= 0 && eventGridY < 10) {
			guess = new Point(eventGridX, eventGridY);
			crosshair.setLayoutX((eventGridX * 40) + gridAnchorPane.getLayoutX() + guessBoard.getLayoutX());
			crosshair.setLayoutY((eventGridY * 40) + gridAnchorPane.getLayoutY() + guessBoard.getLayoutY());
			crosshair.setVisible(true);
		}
	}

	public void fireClicked() throws Throwable {
		// TODO tell the user if they didn't select a target
		System.out.println("FIRE");
		if (guess == null) {

			System.out.println("No Guess");
			return;
		}

		player.setTotalShots(player.getTotalShots() + 1);
		Flower hitFlower = player.getOpponent().getBoard().takeHit(guess.x, guess.y, true);
		if (hitFlower != null) {
			player.setHits(player.getHits() + 1);

			// Below calculates multiplier for hits and adds to player score
			switch (hitFlower.getType().toString()) {
			case "Rose":
				player.setScore(player.getScore() + 3000 * roseMultiplier);
				System.out.println(player.getScore());
				break;
			case "Carnation":
				player.setScore(player.getScore() + 3000 * carnationMultiplier);
				System.out.println(player.getScore());
				break;
			case "Pansy":
				player.setScore(player.getScore() + 3000 * pansyMultiplier);
				System.out.println(player.getScore());
				break;
			case "Tulip":
				player.setScore(player.getScore() + 3000 * tulipMultiplier);
				System.out.println(player.getScore());
				break;
			case "Daisy":
				player.setScore(player.getScore() + 3000 * daisyMultiplier);
				System.out.println(player.getScore());
				break;

			default:
				player.setScore(player.getScore() + 3000);
				System.out.println(player.getScore());
			}

			if (!player.getOpponent().getBoard().isFlowerAlive(hitFlower)) {
				player.setShipsSunk(player.getShipsSunk() + 1);
				hitFlower.getIcon().setVisible(false);
				pieceStatus();
			}

			// Check if the player won
			if (player.getOpponent().getBoard().isGameOver()) {
				// If they have end the game by launching score screen
				launchGameOver(player);
			}
			// Place hits and misses on both boards
			setHitsAndMisses(player.getBoard(), playerBoard);
			setHitsAndMisses(player.getOpponent().getBoard(), guessBoard);

		} else {
			System.out.println("Cell missed");
			// removes 150 points from score if a miss
			player.setScore(player.getScore() - 150);
			// Turn is over if you miss
			switchPlayer();
		}
	}

	public void setHitsAndMisses(Board board, GridPane gridPane) {

		ArrayList<ImageView> hitImages = new ArrayList<>();
		ArrayList<ImageView> missImages = new ArrayList<>();
		ArrayList<Point> hits = board.getHits();
		ArrayList<Point> misses = board.getMisses();

		for (int i = 0; i < hits.size(); i++) {
			hitImages.add(new ImageView(board.getHitImage()));
			hitImages.get(i).setFitHeight(40);
			hitImages.get(i).setFitWidth(40);
			hitImages.get(i).setLayoutX((hits.get(i).x * 40) + (gridAnchorPane.getLayoutX() + gridPane.getLayoutX()));
			hitImages.get(i).setLayoutY((hits.get(i).y * 40) + (gridAnchorPane.getLayoutY() + gridPane.getLayoutY()));
			inGamePane.getChildren().add(hitImages.get(i));
		}

		for (int i = 0; i < misses.size(); i++) {
			missImages.add(new ImageView(board.getMissImage()));
			missImages.get(i).setFitHeight(40);
			missImages.get(i).setFitWidth(40);
			missImages.get(i)
					.setLayoutX((misses.get(i).x * 40) + (gridAnchorPane.getLayoutX() + gridPane.getLayoutX()));
			missImages.get(i)
					.setLayoutY((misses.get(i).y * 40) + (gridAnchorPane.getLayoutY() + gridPane.getLayoutY()));
			inGamePane.getChildren().add(missImages.get(i));
		}
	}

	public void switchPlayer() throws IOException {
		// TODO add timer or something so you can see your shot before it swaps
		// player
		launchGame();
	}

	public void skipClicked() throws IOException {
		// Event handler for skip button
		switchPlayer();
	}

	public void surrenderClicked() throws IOException {
		// Event handler for surrender button
		// Causes current player to lose
		launchGameOver(player.getOpponent());

	}

	public void launchGameOver(Player winningPlayer) throws IOException {
		// Launches game over screen
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CarnationCarnageMain.class.getResource("/CarnationCarnage/FXMLFiles/GameOverScreen.fxml"));
		BorderPane GameOverScreen = loader.load();

		// TODO Pass other player through to game scene
		GameOverController controller = loader.<GameOverController>getController();
		controller.reInitialize(winningPlayer);

		// Show scene
		Stage primaryStage = (Stage) inGamePane.getScene().getWindow();
		primaryStage.setScene(new Scene(GameOverScreen));

	}
}
