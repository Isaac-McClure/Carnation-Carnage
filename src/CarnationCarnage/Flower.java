package CarnationCarnage;

import java.awt.Point;
import java.util.ArrayList;


public class Flower {

    FlowerType type;
    private ArrayList<Point> positions = new ArrayList<Point>();
    private ArrayList<Point> deadPositions = new ArrayList<Point>();
    private boolean orientationIsVertical = false;
    
    private int hits;
    private boolean isAlive;
  
    public Flower(FlowerType type) {
        this.type = type;
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

    public void setOrientationIsVertical(boolean orientationIsVertical) {
        this.orientationIsVertical = orientationIsVertical;
    }
        
    public FlowerType getType() {
        return this.type;
    }
    
}
