package hanoi;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HighScore {
    private final String name;
    private final Integer moves;
    private final String date;

    public HighScore(String name, Integer moves, String date) {
        this.name = name;
        this.moves = moves;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Integer getMoves() {
        return moves;
    }

    public String getDate() {
        return date;
    }

}
