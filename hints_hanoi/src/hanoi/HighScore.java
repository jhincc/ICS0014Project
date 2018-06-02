package hanoi;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HighScore {
    private final StringProperty name;
    private final StringProperty moves;
    private final StringProperty date;

    public HighScore(String name, String moves, String date) {
        this.name = new SimpleStringProperty(name);
        this.moves = new SimpleStringProperty(moves);
        this.date = new SimpleStringProperty(date);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getMoves() {
        return moves.get();
    }

    public StringProperty movesProperty() {
        return moves;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }
}
