package hanoi;


import javafx.scene.shape.Rectangle;

public class Disc extends Rectangle {
    private Double size;

    public Disc(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return size;
    }

}
