package MainFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    public static int questionIdStatic = -1;

    @FXML
    Pane mainPane;

    @FXML
    protected void testButton(ActionEvent event) {
        try {
            Pane newloadedPane = FXMLLoader.load(getClass().getResource("../question/Question.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newloadedPane);
        } catch (IOException e) {

        }
    }

    @FXML
    protected void addQuestionButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../AddQuestion/AddQuestion.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    protected  void questionOverview(ActionEvent event) {
        try {
            Pane newloadedPane2 = FXMLLoader.load(getClass().getResource("../QuestionOverview/QuestionOverview.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newloadedPane2);
        } catch (IOException e) {

        }
    }

}
