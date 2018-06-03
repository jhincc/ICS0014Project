package hanoi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class HanoiGameController implements Initializable {
    @FXML
    private VBox stack1;
    @FXML
    private VBox stack2;
    @FXML
    private VBox stack3;
    @FXML
    private Label movesCounter;


    public void backToSelect(ActionEvent actionEvent) throws IOException {
        Parent selectionParent = FXMLLoader.load(getClass().getResource("Selection.fxml"));
        Scene selectionScene = new Scene(selectionParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(selectionScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        defaultState(stack1, stack2, stack3);

    }

    public Disc createDisc(Integer size){
        Disc disc = new Disc(size);
        disc.setHeight(25);
        disc.setWidth(size * 30);
        disc.setArcHeight(10);
        disc.setArcWidth(10);
        disc.setFill(Color.DODGERBLUE);
        return disc;
    }

    public void resetGame(){
        defaultState(stack1, stack2, stack3);
    }

    private void defaultState(VBox stack1, VBox stack2, VBox stack3) {
        stack1.getChildren().clear();
        stack2.getChildren().clear();
        stack3.getChildren().clear();
        movesCounter.setText("0");

        stack1.getChildren().addAll(createDisc(1), createDisc(2), createDisc(3),createDisc(4));
    }

    public void onDragDetected(MouseEvent event) {
        final Object sourceBox = event.getSource();
        if (sourceBox instanceof VBox) {
            VBox source = (VBox) sourceBox;
            if (!(source.getChildren().isEmpty())) {
                Dragboard drag = source.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                Disc ketas = (Disc) source.getChildren().get(0);
                content.putString(String.valueOf(ketas.getSize()));
                drag.setContent(content);
                event.consume();
            }

        }
    }

    public void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    public void onDragDropped(DragEvent event) {
        final Object sourceBox = event.getGestureSource();
        boolean dragCompleted = false;
        if (sourceBox instanceof VBox) {
            VBox source = (VBox) sourceBox;
            final Object targetBox = event.getGestureTarget();
            if (targetBox instanceof VBox) {
                VBox target = (VBox) targetBox;
                Dragboard drag = event.getDragboard();
                if(drag.hasString()){
                    Integer draggedSize = Integer.valueOf(drag.getString());
                    if(target.getChildren().isEmpty()){
                        target.getChildren().add(0,createDisc(draggedSize));
                        source.getChildren().remove(0);
                        increaseCounter();
                        dragCompleted = true;
                    } else {
                        Disc previous = (Disc) target.getChildren().get(0);
                        if (draggedSize < previous.getSize()){
                            target.getChildren().add(0,createDisc(draggedSize));
                            source.getChildren().remove(0);
                            increaseCounter();
                            dragCompleted = true;
                            if(target.getId() != stack1.getId()){
                                checkVictory(target);
                            }
                                                    }
                    }
                }
                event.setDropCompleted(dragCompleted);
                event.consume();

            }
        }
    }

    public void onDragDone(DragEvent event){
        event.consume();
    }

    private void increaseCounter() {
        movesCounter.setText(String.valueOf(Integer.valueOf(movesCounter.getText()) + 1));
    }

    public void checkVictory(VBox vbox){
        if (vbox.getChildren().size() == 4){
            TextInputDialog dialog = new TextInputDialog("Player");
            dialog.setTitle("You won!");
            if (Integer.valueOf(movesCounter.getText())== 15){
                dialog.setHeaderText("Congratulations, a perfect score!\n Do you wish to store your score in the database?");
            } else {
                dialog.setHeaderText("Congratulations, you made "+ movesCounter.getText() +" moves!\nDo you wish to store your score in the database?");
            }
            dialog.setContentText("Please enter your name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> insertScore(name,Integer.valueOf(movesCounter.getText())));

        }

    }

    private void insertScore(String name, Integer moves){
        try {
            Connection con = DBConnector.getConnection();
            String query = " insert into scores (name, moves)" + " values (?,?)";
            PreparedStatement prep = con.prepareStatement(query);
            prep.setString(1, name);
            prep.setInt(2, moves);
            prep.execute();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


