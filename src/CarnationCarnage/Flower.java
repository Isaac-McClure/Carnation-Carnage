package CarnationCarnage;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.ImageView;


public class Flower {

    FlowerType type;
    private ArrayList<Point> positions = new ArrayList<Point>();
    private ArrayList<Point> deadPositions = new ArrayList<Point>();
    private boolean orientationIsVertical = false;
    
    private ImageView icon;
    
    private int hits;
    private boolean isAlive;
  
    public Flower(FlowerType type) {
        this.type = type;
        this.positions.add(new Point(-1,-1));
        this.icon = new ImageView(this.type.getIconURL());
        // Setup image size and properties
        this.icon.setPreserveRatio(false);
        this.icon.setFitHeight(40);
        this.icon.setFitWidth(this.type.getLength() * 40);
    }
    
    public boolean checkShot(Point coordinate, boolean real) {
        // Checks to see if the flower was hit, removes square from flower if so
        // real is a flag set to false when using this method for placement validation
        if (positions.contains(coordinate)){         
            if (real) {
                hits += 1;
                positions.remove(coordinate);
                deadPositions.add(coordinate);
            }
            return true;
        }       
        else {
            return false;
        }
    }
    
    public boolean checkAlive() {
        // Checks to see if flower is dead, returns true if it is alive
        if (hits == type.getLength()) {
            return false;
        }
        else {return true;}
    }
    
    public ArrayList<Point> getPositions(){
        return positions;
    }
    
    public ArrayList<Point> getDeadPositions(){
        return deadPositions;
    }
    
    public void setPositions(ArrayList<Point> inPositions) {
        this.positions = inPositions;
    }

    public boolean isOrientationIsVertical() {
        return orientationIsVertical;
    }

    public void setOrientationIsVertical(boolean inOrientationIsVertical) {
        this.orientationIsVertical = inOrientationIsVertical;
    }
        
    public FlowerType getType() {
        return this.type;
    }
    
    public ImageView getIcon() {
        return this.icon;
    }
    
    // Clears event handlers
    public void clearImageConfig() {
        this.icon.setOnMouseClicked(null);
        this.icon.setOnMouseDragged(null);
        this.icon.setOnMouseReleased(null);
    }
    
}
