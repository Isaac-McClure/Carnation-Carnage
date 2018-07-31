package CarnationCarnage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

public class Board {
    // Keeps track of pieces
    private ArrayList<Flower> peicesRemaining;
    
    private Flower Rose;
    private Flower Carnation;
    private Flower Pansy;
    private Flower Tulip;
    private Flower Daisy;
    
    Board(){      
        // initialize all pieces
        peicesRemaining = new ArrayList<Flower>();
        
        Rose = new Flower(FlowerType.Rose);
        Carnation = new Flower(FlowerType.Carnation);
        Pansy = new Flower(FlowerType.Pansy);
        Tulip = new Flower(FlowerType.Tulip);
        Daisy = new Flower(FlowerType.Daisy);        
        
        // add to list so we can iterate through them
        peicesRemaining.add(Rose);
        peicesRemaining.add(Carnation);
        peicesRemaining.add(Pansy);
        peicesRemaining.add(Tulip);
        peicesRemaining.add(Daisy);
    }
    
    public Flower takeHit(int x, int y, boolean real) {
        // Checks an incoming shot to see what was hit
        Point coordinate = new Point(x,y); 
    
        for(int i = 0; i < peicesRemaining.size(); i++) {
            if (peicesRemaining.get(i).checkShot(coordinate, real)) {
               return peicesRemaining.get(i);
            };
        }
        
        // if we get here, the shot must have missed all living flowers
        return null;
    }
    
    public boolean isFlowerDead(Flower flower) {
       return flower.checkAlive();
    }
    
    public Flower getRose() {
        return Rose;
    }

    public Flower getCarnation() {
        return Carnation;
    }
    public Flower getPansy() {
        return Pansy;
    }
    
    public Flower getTulip() {
        return Tulip;
    }
    
    public Flower getDaisy() {
        return Daisy;
    }
}
