package CarnationCarnage;

import javafx.scene.image.ImageView;
import java.awt.Point;

public enum FlowerType {
  //Maps flowers with lengths
    Rose("Rose", 5, "/CarnationCarnage/Flowers/Rose.png"),
    Carnation("Carnation", 4, "/CarnationCarnage/Flowers/Carnationdl.png"),
    Pansy("Pansy", 3, "/CarnationCarnage/Flowers/Pansy.jpg"),
    Tulip("Tulip", 3, "/CarnationCarnage/Flowers/tulip.png"),
    Daisy("Daisy", 2, "/CarnationCarnage/Flowers/Daisy.png");
    
    private String name;
    private int length;
    private String iconURL;
    
    
    FlowerType(String name, int length, String iconURL) {
        this.name = name;
        this.length = length;
        this.iconURL = iconURL;
        
    }
    
    int getLength() {
        return this.length;
    }
    
    String getName() {
        return this.name;
    }
    
    String getIconURL() {
        return this.iconURL;
    }
    
}
