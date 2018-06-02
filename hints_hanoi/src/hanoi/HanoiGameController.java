package hanoi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HanoiGameController implements Initializable {
    @FXML
    private VBox stack1;
    @FXML
    private VBox stack2;
    @FXML
    private VBox stack3;

    Disc disc1 = new Disc(1.0);
    Disc disc2 = new Disc(2.0);
    Disc disc3 = new Disc(3.0);
    Disc disc4 = new Disc(4.0);

    public void backToSelect(ActionEvent actionEvent) throws IOException {
        Parent selectionParent = FXMLLoader.load(getClass().getResource("Selection.fxml"));
        Scene selectionScene = new Scene(selectionParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(selectionScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        disc4.setHeight(25);
        disc4.setWidth(disc4.getSize() * 30);
        disc4.setFill(Color.DODGERBLUE);

        disc3.setHeight(25);
        disc3.setWidth(disc3.getSize() * 30);
        disc3.setFill(Color.DODGERBLUE);

        disc2.setHeight(25);
        disc2.setWidth(disc2.getSize() * 30);
        disc2.setFill(Color.DODGERBLUE);

        disc1.setHeight(25);
        disc1.setWidth(disc1.getSize() * 30);
        disc1.setFill(Color.DODGERBLUE);





    }

}
