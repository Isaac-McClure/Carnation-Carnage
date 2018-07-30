package CarnationCarnage;

import javafx.scene.image.Image;

public enum FlowerType {
  //Maps flowers with lengths
    Rose("Rose", 5, new Image("/CarnationCarnage/Flowers/Rose.png")),
    Carnation("Carnation", 4, new Image("/CarnationCarnage/Flowers/Carnationdl.png")),
    Pansy("Pansy", 3, new Image("/CarnationCarnage/Flowers/Pansy.jpg")),
    Tulip("Tulip", 3, new Image("/CarnationCarnage/Flowers/tulip.png")),
    Daisy("Daisy", 2, new Image("/CarnationCarnage/Flowers/Daisy.png"));
    
    private String name;
    private int length;
    private Image icon;
    
    FlowerType(String name, int length, Image icon) {
        this.name = name;
        this.length = length;
        this.icon = icon;
    }
    
    int getLength() {
        return this.length;
    }
    
    String getName() {
        return this.name;
    }
    
    Image getIcon() {
        return this.icon;
    }
}
