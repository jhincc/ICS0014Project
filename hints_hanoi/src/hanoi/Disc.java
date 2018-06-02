package hanoi;


import javafx.scene.shape.Rectangle;

public class Disc extends Rectangle {
    private Integer size;

    public Disc(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

}
