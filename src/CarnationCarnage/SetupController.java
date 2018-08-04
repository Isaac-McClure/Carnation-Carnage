package CarnationCarnage;

import java.awt.Point;

// Drag and drop code inspired by https://stackoverflow.com/questions/38513111/javafx-how-to-drag-imageview-on-a-pane 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import CarnationCarnage.FlowerType;

public class SetupController {
    // This class displays a the setup screen
    @FXML
    private AnchorPane setupPane;
    @FXML
    private AnchorPane gridAnchorPane;
    @FXML
    Label currentPlayerLabel;

    ImageView roseImage;
    ImageView carnationImage;
    ImageView pansyImage;
    ImageView tulipImage;
    ImageView daisyImage;

    Point roseDefaultPosition;
    Point carnationDefaultPosition;
    Point pansyDefaultPosition;
    Point tulipDefaultPosition;
    Point daisyDefaultPosition;

    @FXML
    GridPane setupGrid;

    private Player player;

    private Hashtable<ImageView, Flower> imageToFlower = new Hashtable<ImageView, Flower>();
    private Hashtable<ImageView, Point> imageToDefaultPosition = new Hashtable<ImageView, Point>();

    public void initialize() {
        roseDefaultPosition = new Point(700, 120);
        carnationDefaultPosition = new Point(700, 165);
        pansyDefaultPosition = new Point(700, 210);
        tulipDefaultPosition = new Point(700, 255);
        daisyDefaultPosition = new Point(700, 300);
    }

    // This method is called after initialize so we can pass in player first
    public void reInitialise() {
        // Set up graphics
        currentPlayerLabel.setText(player.getName() + "'s Turn to Setup");

        // Set flowers in position with appropriate event handlers and
        // ImageViews
        roseImage = player.getBoard().getRose().getIcon();
        roseImage.setLayoutX(roseDefaultPosition.x);
        roseImage.setLayoutY(roseDefaultPosition.y);
        roseImage.setOnMouseClicked(this::flowerClicked);
        roseImage.setOnMouseDragged(this::flowerDragOverEvent);
        roseImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(roseImage);
        // Add mapping to actual flower object, and to default position for
        // later
        imageToFlower.put(roseImage, player.getBoard().getRose());
        imageToDefaultPosition.put(roseImage, roseDefaultPosition);

        carnationImage = player.getBoard().getCarnation().getIcon();
        carnationImage.setLayoutX(carnationDefaultPosition.x);
        carnationImage.setLayoutY(carnationDefaultPosition.y);
        carnationImage.setOnMouseClicked(this::flowerClicked);
        carnationImage.setOnMouseDragged(this::flowerDragOverEvent);
        carnationImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(carnationImage);
        // Add mapping to actual flower object, and to default position for
        // later
        imageToFlower.put(carnationImage, player.getBoard().getCarnation());
        imageToDefaultPosition.put(carnationImage, carnationDefaultPosition);

        pansyImage = player.getBoard().getPansy().getIcon();
        pansyImage.setLayoutX(pansyDefaultPosition.x);
        pansyImage.setLayoutY(pansyDefaultPosition.y);
        pansyImage.setOnMouseClicked(this::flowerClicked);
        pansyImage.setOnMouseDragged(this::flowerDragOverEvent);
        pansyImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(pansyImage);
        // Add mapping to actual flower object, and to default position for
        // later
        imageToFlower.put(pansyImage, player.getBoard().getPansy());
        imageToDefaultPosition.put(pansyImage, pansyDefaultPosition);

        tulipImage = player.getBoard().getTulip().getIcon();
        tulipImage.setLayoutX(tulipDefaultPosition.x);
        tulipImage.setLayoutY(tulipDefaultPosition.y);
        tulipImage.setOnMouseClicked(this::flowerClicked);
        tulipImage.setOnMouseDragged(this::flowerDragOverEvent);
        tulipImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(tulipImage);
        // Add mapping to actual flower object, and to default position for
        // later
        imageToFlower.put(tulipImage, player.getBoard().getTulip());
        imageToDefaultPosition.put(tulipImage, tulipDefaultPosition);

        daisyImage = player.getBoard().getDaisy().getIcon();
        daisyImage.setLayoutX(daisyDefaultPosition.x);
        daisyImage.setLayoutY(daisyDefaultPosition.y);
        daisyImage.setOnMouseClicked(this::flowerClicked);
        daisyImage.setOnMouseDragged(this::flowerDragOverEvent);
        daisyImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(daisyImage);
        // Add mapping to actual flower object, and to default position for
        // later
        imageToFlower.put(daisyImage, player.getBoard().getDaisy());
        imageToDefaultPosition.put(daisyImage, daisyDefaultPosition);

    }

    // Calls launch game if both players are done, and
    // Saves and resets the board if only one is.
    @FXML
    private void doneClicked() throws IOException {
        // validate placement
        if (validatePlacement()) {
            player.setDoneSetup(true);

            System.out.println("PlacementValid");
            // Check if other player is done
            if (!player.getOpponent().getDoneSetup()) {
                launchSetupForOpponent();
            } else {
                launchGame();
            }
        } else {

        }

    }

    private void launchSetupForOpponent() throws IOException {
        System.out.println("LAUNCH SETUP");

        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class
                .getResource("/CarnationCarnage/FXMLFiles/SetupScreen.fxml"));
        AnchorPane setupScreen = loader.load();
        Stage primaryStage = (Stage) setupPane.getScene().getWindow();

        // Pass the second player to the SetupController
        SetupController controller = loader.<SetupController>getController();
        controller.setPlayer(player.getOpponent());
        controller.reInitialise();

        // Show scene
        primaryStage.setScene(new Scene(setupScreen));
    }

    // Launches the inGameScene
    private void launchGame() throws IOException {
        // clear flower event handlers
        ArrayList<Flower> flowersToClear = new ArrayList<>(player.getBoard().getPeicesRemaining());
        flowersToClear.addAll(player.getOpponent().getBoard().getPeicesRemaining());
        for(Flower flowerToClear: flowersToClear) {
            flowerToClear.clearImageConfig();
        }
        
        // Load Scene
        System.out.println("LAUNCH GAME");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class
                .getResource("/CarnationCarnage/FXMLFiles/inGameScene.fxml"));
        AnchorPane playScreen = loader.load();

        // TODO Pass first player through to game scene
        InGameController controller = loader.<InGameController>getController();
        controller.setPlayer(player.getOpponent());
        controller.reInitialise();

        // Show scene
        Stage primaryStage = (Stage) setupPane.getScene().getWindow();
        primaryStage.setScene(new Scene(playScreen));
    }

    public void setPlayer(Player inPlayer) {
        player = inPlayer;
    }

    @FXML
    public void flowerClicked(MouseEvent event) {
        // Check if it is a right click, and rotate if so.
        // the inbuilt rotate function seems to only spin the image and keeps
        // the hit-box the same

    	ImageView flower = ((ImageView) event.getTarget());

        Flower flowerRotated = imageToFlower.get(flower);
        boolean vertical = flowerRotated.isOrientationIsVertical();
        System.out.print(vertical);
               
        if (event.getButton() == MouseButton.SECONDARY) {      	
        	if(vertical==false) {
        	
        	flower.getTransforms().add(new Rotate(90,20,20));             	
        	
            // Update flower object
            flowerRotated = imageToFlower.get(flower);
            flowerRotated.setOrientationIsVertical(
                    !vertical);
        }
        	else                 	
                	flower.getTransforms().add(new Rotate(-90,20,20));             	             	
                    // Update flower object
                    flowerRotated = imageToFlower.get(flower);
                    flowerRotated.setOrientationIsVertical(
                            !vertical);              
        }        
        event.consume();
    }

    @FXML
    public void flowerDragDroppedEvent(MouseEvent event) {
        // Snaps flower to the nearest grid square, or back to the original
        // position if the flower is not on the grid.
        ImageView flower = ((ImageView) event.getTarget());
        System.out.println(flower.getLayoutX() + " " + flower.getLayoutY());

        // Convert event location to be relative to the grid
        double xRelativeToGrid = flower.getLayoutX()
                - gridAnchorPane.getLayoutX();
        double yRelativeToGrid = flower.getLayoutY()
                - gridAnchorPane.getLayoutY();

        // Find grid square from above information
        int gridX = (int) xRelativeToGrid / 40;
        int gridY = (int) yRelativeToGrid / 40;

        // Snap to grid
        flower.setLayoutX((gridX * 40) + gridAnchorPane.getLayoutX());
        flower.setLayoutY((gridY * 40) + gridAnchorPane.getLayoutY());

        Flower flowerDropped = imageToFlower.get(flower);

        // add positions to the list
        ArrayList<Point> positions = new ArrayList<Point>();
        if (flowerDropped.isOrientationIsVertical()) {
            // if vertical add length downwards for each position
            for (int i = 0; i < flowerDropped.getType().getLength(); i++) {
                positions.add(new Point(gridX, gridY + i));
            }
        } else {
            // if vertical add length sideways for each position
            for (int i = 0; i < flowerDropped.getType().getLength(); i++) {
                positions.add(new Point(gridX + i, gridY));
            }
        }

        // clear any previous positions, set it to an invalid position
        // to force reset if placement is invalid
        ArrayList<Point> defaultGridPosition = new ArrayList<Point>();
        defaultGridPosition.add(new Point(-1, -1));
        flowerDropped.setPositions(defaultGridPosition);

        // Validate Positions, otherwise put back to default position
        // No position can be out of grid or on another flower
        Boolean placementValid = true;
        for (Point position : positions) {
            if (player.getBoard().takeHit(position.x, position.y, false) == null
                    && position.x >= 0 && position.x < 10 && position.y >= 0
                    && position.y < 10) {
                continue;
            } else {
                flower.setLayoutX(imageToDefaultPosition.get(flower).x);
                flower.setLayoutY(imageToDefaultPosition.get(flower).y);
                placementValid = false;
                break;
            }
        }

        // If all is well set positions
        if (placementValid) {
            flowerDropped.setPositions(positions);
        }
    }

    @FXML
    public void flowerDragOverEvent(MouseEvent event) {
        // Moves the flower with the mouse
        ((Node) event.getTarget()).setLayoutX(event.getSceneX());
        ((Node) event.getTarget()).setLayoutY(event.getSceneY());

    }

    // Returns true if pieces are valid
    public boolean validatePlacement() {

        Boolean placementValid = true;
        ArrayList<Flower> pieces = player.getBoard().getPeicesRemaining();

        for (Flower piece : pieces) {
            ArrayList<Point> positions = piece.getPositions();

            for (Point position : positions) {
                if (position.x >= 0 && position.x < 10 && position.y >= 0
                        && position.y < 10) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return placementValid;
    }

}
