package CarnationCarnage;

import java.awt.Point;

// Drag and drop code inspired by https://stackoverflow.com/questions/38513111/javafx-how-to-drag-imageview-on-a-pane 

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

public class SetupController {
    // This class displays a the setup screen
    @FXML
    private  AnchorPane setupPane;
    @FXML
    private  AnchorPane gridAnchorPane;
    @FXML
    Label currentPlayerLabel;
    
    ImageView roseImage;
    ImageView carnationImage;
    ImageView pansyImage;
    ImageView tulipImage;
    ImageView daisyImage;
    
    @FXML
    GridPane setupGrid;
    
    private Player player;
    
    private Hashtable<ImageView, Flower> imageToFlower = new Hashtable<ImageView, Flower>();

    public void initialize() {    }

    // Calls launch game if both players are done, and
    // Saves and resets the board if only one is.
    @FXML
    private void doneClicked() {

    }

    // Launches the inGameScene
    private void launchGame() throws IOException {
        // Load Scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CarnationCarnageMain.class
                .getResource("/CarnationCarnage/FXMLFiles/inGameScene.fxml"));
        BorderPane playScreen = loader.load();
        
        // TODO Pass first player through to game scene
        
        
        // Show scene
        Stage primaryStage = (Stage) setupPane.getScene().getWindow();
        primaryStage.setScene(new Scene(playScreen));
    }

    public void setPlayer(Player inPlayer) {
        player = inPlayer;
    }

    public void reInitialise() {
        // Set up graphics 
        currentPlayerLabel.setText(player.getName() + "'s Turn to Setup");
        
        // Set flowers in position with appropriate event handlers and ImageViews
        roseImage = player.getBoard().getRose().getType().getIcon();
        roseImage.setLayoutX(player.getBoard().getRose().getType().getDefaultPosition().x);
        roseImage.setLayoutY(player.getBoard().getRose().getType().getDefaultPosition().y);
        roseImage.setOnMouseClicked(this::flowerClicked);
        roseImage.setOnMouseDragged(this::flowerDragOverEvent);
        roseImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(roseImage);
        // Add mapping to actual flower object
        imageToFlower.put(roseImage, player.getBoard().getRose());
        
        carnationImage = player.getBoard().getCarnation().getType().getIcon(); 
        carnationImage.setLayoutX(player.getBoard().getCarnation().getType().getDefaultPosition().x);
        carnationImage.setLayoutY(player.getBoard().getCarnation().getType().getDefaultPosition().y);   
        carnationImage.setOnMouseClicked(this::flowerClicked);
        carnationImage.setOnMouseDragged(this::flowerDragOverEvent);
        carnationImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(carnationImage);
        // Add mapping to actual flower object
        imageToFlower.put(carnationImage, player.getBoard().getCarnation());
        
        pansyImage = player.getBoard().getPansy().getType().getIcon();      
        pansyImage.setLayoutX(player.getBoard().getPansy().getType().getDefaultPosition().x);
        pansyImage.setLayoutY(player.getBoard().getPansy().getType().getDefaultPosition().y);  
        pansyImage.setOnMouseClicked(this::flowerClicked);
        pansyImage.setOnMouseDragged(this::flowerDragOverEvent);
        pansyImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(pansyImage);
        // Add mapping to actual flower object
        imageToFlower.put(pansyImage, player.getBoard().getPansy());
        
        tulipImage = player.getBoard().getTulip().getType().getIcon();    
        tulipImage.setLayoutX(player.getBoard().getTulip().getType().getDefaultPosition().x);
        tulipImage.setLayoutY(player.getBoard().getTulip().getType().getDefaultPosition().y);    
        tulipImage.setOnMouseClicked(this::flowerClicked);
        tulipImage.setOnMouseDragged(this::flowerDragOverEvent);
        tulipImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(tulipImage);
        // Add mapping to actual flower object
        imageToFlower.put(tulipImage, player.getBoard().getTulip());
        
        daisyImage = player.getBoard().getDaisy().getType().getIcon();
        daisyImage.setLayoutX(player.getBoard().getDaisy().getType().getDefaultPosition().x);
        daisyImage.setLayoutY(player.getBoard().getDaisy().getType().getDefaultPosition().y);
        daisyImage.setOnMouseClicked(this::flowerClicked);
        daisyImage.setOnMouseDragged(this::flowerDragOverEvent);
        daisyImage.setOnMouseReleased(this::flowerDragDroppedEvent);
        setupPane.getChildren().add(daisyImage);
        // Add mapping to actual flower object
        imageToFlower.put(daisyImage, player.getBoard().getDaisy());
        
        
    }
    
    @FXML
    public void flowerClicked(MouseEvent event) {
        // Check if it is a right click, and rotate if so.
        // the inbuilt rotate function seems to only spin the image and keeps the hit-box the same
        // so swap width and height instead. Might cause issues when images are different.
        ImageView flower = ((ImageView) event.getTarget());
        
        double oldHeight = flower.getFitHeight();
        double oldWidth = flower.getFitWidth();
        
        if(event.getButton() == MouseButton.SECONDARY) 
        {
            flower.setFitHeight(oldWidth);
            flower.setFitWidth(oldHeight);
            // Update flower object
            Flower flowerRotated = imageToFlower.get(flower);
            flowerRotated.setOrientationIsVertical(!flowerRotated.isOrientationIsVertical());
        }
        event.consume();
    }
    
    @FXML
    public void flowerDragDroppedEvent(MouseEvent event) {
        // TODO Must snap to the nearest grid square, or back to the original position if the flower is not on the grid. 
        ImageView flower = ((ImageView) event.getTarget());
        System.out.println(flower.getLayoutX() + " " + flower.getLayoutY());
        
        // Convert event location to be relative to the grid
        double xRelativeToGrid = flower.getLayoutX() - gridAnchorPane.getLayoutX();
        double yRelativeToGrid = flower.getLayoutY() - gridAnchorPane.getLayoutY();
        
        // Find grid square from above information
        int gridX = (int) xRelativeToGrid / 40;
        int gridY = (int) yRelativeToGrid / 40;
        
        // Snap to grid
        flower.setLayoutX((gridX*40) + gridAnchorPane.getLayoutX());
        flower.setLayoutY((gridY*40) + gridAnchorPane.getLayoutY());
        
        Flower flowerDropped = imageToFlower.get(flower);
        
        // add positions to the list
        ArrayList<Point> positions = new ArrayList<Point>();
        if (flowerDropped.isOrientationIsVertical()){
            // if vertical add length downwards for each position
            for(int i = 0; i < flowerDropped.getType().getLength(); i++) {
                positions.add(new Point(gridX, gridY+i));
            }
        }
        else {
            // if vertical add length sideways for each position
            for(int i = 0; i < flowerDropped.getType().getLength(); i++) {
                positions.add(new Point(gridX+i, gridY));           
            }
        }
        
        // clear any previous positions
        flowerDropped.setPositions(new ArrayList<Point>());
        
        // Validate Positions, otherwise put back to default position
        // No position can be out of grid or on another flower
        Boolean placementValid = true;
        for(Point position: positions) {
            System.out.println(player.getBoard().takeHit(position.x, position.y, false) == null);
            if(player.getBoard().takeHit(position.x, position.y, false) == null && position.x >= 0 && position.x <10 && position.y >= 0 && position.y <10) {               
                continue;
            }
            else {
                flower.setLayoutX(flowerDropped.getType().getDefaultPosition().x);
                flower.setLayoutY(flowerDropped.getType().getDefaultPosition().y);
                placementValid = false;
                break;
            }
        }

        // If all is well set positions
        if(placementValid) {
        flowerDropped.setPositions(positions);
        }
    }
    
    @FXML
    public void flowerDragOverEvent(MouseEvent event) {
        // Moves the flower with the mouse
        ImageView draggedFlower = (ImageView) event.getTarget();
        
        ((Node) event.getTarget()).setLayoutX(event.getSceneX());
        ((Node) event.getTarget()).setLayoutY(event.getSceneY());
         
    }
    
}
