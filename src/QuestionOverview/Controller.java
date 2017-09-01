package QuestionOverview;

import Model.QuestionObject;
import Sql.QuestionOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    TableView table;

    @FXML
    AnchorPane testPane;

    @FXML
    Pane mainPane;


    private ArrayList<QuestionObject> questionList = new ArrayList<>();

    private ObservableList<QuestionObject> createList() {
        ObservableList<QuestionObject> test = FXCollections.observableArrayList();

        ResultSet rs = new QuestionOverview().questionList();

        try {
            while (rs.next()) {
                QuestionObject obj = new QuestionObject(rs.getString("text"), rs.getInt("id"));
                test.add(obj);
                questionList.add(obj);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return test;
    }

    @FXML
    protected void listButton(ActionEvent event) {


    }

    @FXML
    public void schowButton() {

        try {
            System.out.println(questionList.get(table.getSelectionModel().getSelectedIndex()).getQuestionId());
            Pane newloadedPane = FXMLLoader.load(getClass().getResource("../question/Question.fxml"));
            mainPane.getChildren().clear();
            MainFrame.Controller.questionIdStatic = questionList.get(table.getSelectionModel().getSelectedIndex()).getQuestionId();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getColumns().clear();
        TableColumn<QuestionObject, String> junk = new TableColumn<QuestionObject, String>("test");

        junk.setCellValueFactory((param -> {
            return param.getValue().textProperty();
        }));

        table.getColumns().addAll(junk);
        table.setItems(createList());
    }
}





