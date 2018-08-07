package CarnationCarnage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// Fire icon made by Vectors Market from flaticon.com
// Miss icon made by Google from flaticon.com
public class Board {
    // Keeps track of pieces, as well as hits and misses
    private ArrayList<Flower> peicesRemaining;
    private ArrayList<Point> hits;
    private ArrayList<Point> misses;
    
    private Image missImage;
    private Image hitImage;
    
    private Flower Rose;
    private Flower Carnation;
    private Flower Pansy;
    private Flower Tulip;
    private Flower Daisy;
    
    Board(){      
        // initialize all pieces
        peicesRemaining = new ArrayList<Flower>();
        hits = new ArrayList<Point>();
        misses = new ArrayList<Point>();
        
        // image setup
        missImage = (new Image("/CarnationCarnage/Images/Miss.png"));
        hitImage = (new Image("/CarnationCarnage/Images/Hit.png"));
        
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
        
        // If that coordinate has already been shot do nothing
        if(hits.contains(coordinate) || misses.contains(coordinate)) {
            return null;
        }
        
        for(int i = 0; i < peicesRemaining.size(); i++) {
            if (peicesRemaining.get(i).checkShot(coordinate, real)) {
                Flower hitFlower = peicesRemaining.get(i);
                if (real) {
                    hits.add(new Point(x,y));
                    if(!isFlowerAlive(hitFlower)) {
                        peicesRemaining.remove(hitFlower);
                    }
                    return hitFlower;
                }
                else {
                    return hitFlower;
                }
            };
        }
        
        // if we get here, the shot must have missed all living flowers
        if(real) {
            misses.add(new Point(x,y));
            }
        return null;
    }
    
    public boolean isGameOver() {
       return peicesRemaining.isEmpty();
    }
    
    public boolean isFlowerAlive(Flower flower) {
       return flower.checkAlive();
    }
    
    public ArrayList<Flower> getPeicesRemaining(){
        return peicesRemaining;
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

    public Image getHitImage() {
        return hitImage;
    }

    public Image getMissImage() {
        return missImage;
    }

    public ArrayList<Point> getMisses() {
        return misses;
    }
    
    public ArrayList<Point> getHits() {
        return hits;
    }

}  
