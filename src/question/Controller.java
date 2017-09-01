package question;

import Sql.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Sql.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import MainFrame.*;


public class Controller implements Initializable {

    @FXML
    Label labelMaxQuestions;

    @FXML
    TextArea questionField;

    @FXML
    Pane paneAnswers;

    @FXML
    ToggleGroup group = new ToggleGroup();

    @FXML
    AnchorPane testPane;

    private ArrayList<Model.Question> awnserList;
    private String[] list;
    private ArrayList<CheckBox> cbList;
    private Statement stat = new Connect().connecting();
    private ArrayList<Integer> questionIdList = new ArrayList<>();


    @FXML
    protected void randomQuestionButton() {
        MainFrame.Controller.questionIdStatic = -1;
        questionCreate();

    }

    public void questionCreate() {
        if (MainFrame.Controller.questionIdStatic == -1) {
            int maxQuestion = new Question().getMaxQuestion();
            int rand = new Random().nextInt(maxQuestion);

            try {
                ResultSet rs = stat.executeQuery("SELECT id FROM questions");
                while (rs.next())
                    questionIdList.add(rs.getInt("id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MainFrame.Controller.questionIdStatic = questionIdList.get(rand);
        }
        list = new Question().getQuestion(MainFrame.Controller.questionIdStatic);
        questionField.clear();
        questionField.appendText("" + list[0]);

        awnserList = new Question().getAwnsers(MainFrame.Controller.questionIdStatic);
        System.out.println(list[1]);
        if (list[1].equals("1")) {
            group = new ToggleGroup();
            paneAnswers.getChildren().clear();
            for (Model.Question i : awnserList) {
                RadioButton rb = new RadioButton(i.text);
                rb.setToggleGroup(group);
                paneAnswers.getChildren().add(rb);

            }
        } else if (list[1].equals("2")) {
            cbList = new ArrayList<>();
            int index = 0;
            paneAnswers.getChildren().clear();
            for (Model.Question i : awnserList) {
                CheckBox cb = new CheckBox(i.text);
                cbList.add(cb);
            }

            for (CheckBox i : cbList) {
                paneAnswers.getChildren().add(i);
            }
        }

    }


    @FXML
    public void buttonSubmit(ActionEvent event) {
        boolean rightAwnser = false;
        int maxRightAwnsers = 0;
        int rightAwnsers = 0;
        int wrongAwnsers = 0;
        if (list[1].equals("1")) {
            RadioButton button = (RadioButton) group.getSelectedToggle();
            for (Model.Question i : awnserList) {
                if (i.trueOrFalse == 1 && i.text.equals(button.getText())) {
                    rightAwnser = true;
                }
            }
            if (rightAwnser) questionsCorrect();
            else questionFalse();
        } else if (list[1].equals("2")) {
            for (Model.Question i : awnserList)
                if (i.trueOrFalse == 1) {
                    maxRightAwnsers++;
                }

            for (CheckBox i : cbList) {
                for (Model.Question q : awnserList) {
                    if (i.isSelected()) {
                        if (i.getText().equals(q.text) && q.trueOrFalse == 1) {
                            rightAwnsers++;
                            break;
                        } else {
                            wrongAwnsers++;
                            break;
                        }
                    }
                }
            }
            if (maxRightAwnsers == rightAwnsers && wrongAwnsers == 0)
                questionsCorrect();
            else
                questionFalse();
            System.out.println("Max: " + maxRightAwnsers + " Richtig: " + rightAwnsers + " Falsch: " + wrongAwnsers);

        }

    }

    public void questionsCorrect() {
        paneAnswers.setStyle("-fx-border-color: green; -fx-border-width: 5px");
    }

    public void questionFalse() {
        paneAnswers.setStyle("-fx-border-color: red; -fx-border-width: 5px");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionCreate();

    }
}
