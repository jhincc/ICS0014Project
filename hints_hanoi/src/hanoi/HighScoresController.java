package hanoi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {

    @FXML private TableView<HighScore> score;
    @FXML private TableColumn<HighScore, String> col_date;
    @FXML private TableColumn<HighScore, Integer> col_moves;
    @FXML private TableColumn<HighScore, String> col_user;





    public void backToSelection(ActionEvent actionEvent) throws IOException {
        Parent selectionParent = FXMLLoader.load(getClass().getResource("Selection.fxml"));
        Scene selectionScene = new Scene(selectionParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(selectionScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_user.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_moves.setCellValueFactory(new PropertyValueFactory<>("moves"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        allTimeScores();


    }

    public void allTimeScores() {
        score.getItems().clear();
        ObservableList<HighScore> oblist = FXCollections.observableArrayList();
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from scores order by moves asc");
            while (rs.next()){
                oblist.add(new HighScore(rs.getString("name"), rs.getInt("moves"), rs.getString("scoredate")));
            }
            score.setItems(oblist);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void lastMonthScores() {
        score.getItems().clear();
        ObservableList<HighScore> oblist = FXCollections.observableArrayList();
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from scores where scoredate >= date_sub(now(), interval 30 day) order by moves asc");
            while (rs.next()){
                oblist.add(new HighScore(rs.getString("name"), rs.getInt("moves"), rs.getString("scoredate")));
            }
            score.setItems(oblist);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void todayScores() {
        score.getItems().clear();
        ObservableList<HighScore> oblist = FXCollections.observableArrayList();
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from scores where scoredate >= date_sub(now(), interval 1 day) order by moves asc");
            while (rs.next()){
                oblist.add(new HighScore(rs.getString("name"), rs.getInt("moves"), rs.getString("scoredate")));
            }
            score.setItems(oblist);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
