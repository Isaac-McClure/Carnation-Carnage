package CarnationCarnage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

public class Board {
    // Keeps track of pieces on a board
    
    private ArrayList[][] board;
    
    private ArrayList<Flower> peicesRemaining;
    
    Board(){
        // Takes an ArrayList of strings
        // and interprets them as game pieces
        board = new ArrayList[10][10];
        peicesRemaining = new ArrayList<Flower>();
        
        // initialize all pieces
        peicesRemaining.add(new Flower(FlowerType.Rose));
        peicesRemaining.add(new Flower(FlowerType.Carnation));
        peicesRemaining.add(new Flower(FlowerType.Pansy));
        peicesRemaining.add(new Flower(FlowerType.Tulip));
        peicesRemaining.add(new Flower(FlowerType.Daisy));        
    }
    
    public Flower takeHit(int x, int y) {
        // Checks an incoming shot to see what was hit
        Point coordinate = new Point(x,y); 
    
        for(int i = 0; i < peicesRemaining.size(); i++) {
            if (peicesRemaining.get(i).checkShot(coordinate)) {
               return peicesRemaining.get(i);
            };
        }
        
        // if we get here, the shot must have missed all living flowers
        return null;
    }
    
    public boolean isFlowerDead(Flower flower) {
       return flower.checkAlive();
    }
    
}
