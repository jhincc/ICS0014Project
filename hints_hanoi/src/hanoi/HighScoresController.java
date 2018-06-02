package hanoi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {
    @FXML private TableView score;



    public void backToSelection(ActionEvent actionEvent) throws IOException {
        Parent selectionParent = FXMLLoader.load(getClass().getResource("Selection.fxml"));
        Scene selectionScene = new Scene(selectionParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(selectionScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DBConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
