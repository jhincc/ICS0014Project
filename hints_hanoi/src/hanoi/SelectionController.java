package hanoi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionController {
    public void popUpAbout(ActionEvent actionEvent) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Information");
        about.setHeaderText("About this project");
        about.setContentText("Project for ICS0014 Java Technologies \nAuthor: Jarmo-Hendrik Hints \n2018 Tallinn University of Technology");

        about.showAndWait();
    }

    public void openGame(ActionEvent actionEvent) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("HanoiGame.fxml"));
        Scene gameScene = new Scene(gameParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(gameScene);
        window.show();
        
    }

    public void openHighScores(ActionEvent actionEvent) throws IOException {
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("HighScores.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(highScoreScene);
        window.show();
    }

}
