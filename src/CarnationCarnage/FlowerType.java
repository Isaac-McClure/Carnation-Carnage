package CarnationCarnage;

import javafx.scene.image.ImageView;
import java.awt.Point;

public enum FlowerType {
  //Maps flowers with lengths
    Rose("Rose", 5, new ImageView("/CarnationCarnage/Flowers/Rose.png"), new Point(700, 120)),
    Carnation("Carnation", 4, new ImageView("/CarnationCarnage/Flowers/Carnationdl.png"), new Point(700, 165)),
    Pansy("Pansy", 3, new ImageView("/CarnationCarnage/Flowers/Pansy.jpg"), new Point(700, 210)),
    Tulip("Tulip", 3, new ImageView("/CarnationCarnage/Flowers/tulip.png"), new Point(700, 255)),
    Daisy("Daisy", 2, new ImageView("/CarnationCarnage/Flowers/Daisy.png"), new Point(700, 300));
    
    private String name;
    private int length;
    private ImageView icon;
    
    // Used to place in setup
    private Point defaultPosition;
    
    FlowerType(String name, int length, ImageView icon, Point defaultPosition) {
        this.name = name;
        this.length = length;
        this.icon = icon;
        this.defaultPosition = defaultPosition;
        
        // Setup image size and properties
        this.icon.setPreserveRatio(false);
        this.icon.setFitHeight(40);
        this.icon.setFitWidth(length * 40);
    }
    
    int getLength() {
        return this.length;
    }
    
    String getName() {
        return this.name;
    }
    
    ImageView getIcon() {
        return this.icon;
    }
    
    Point getDefaultPosition() {
        return this.defaultPosition;
    }
}
