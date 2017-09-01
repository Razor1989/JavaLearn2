package AddQuestion;


import com.sun.javafx.text.TextLine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.event.MouseInputListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Controller {

    @FXML
    Pane paneAwnsers;

    @FXML
    ToggleGroup choiceTyp;

    @FXML
    TextArea questionText;

    @FXML
    Label questionStatus;

    public ArrayList<TextField> tfList = new ArrayList<>();
    public ArrayList<CheckBox> cbList = new ArrayList<>();
    public ArrayList<RadioButton> rbList = new ArrayList<>();
    public ArrayList<Button> bList = new ArrayList<>();
    public ToggleGroup group = new ToggleGroup();
    public int questionTyp = 0;
    public Statement stat;

    @FXML
    protected void addQuestionButton(ActionEvent event) {
        TextField tf = new TextField();
        tf.setMaxWidth(150);
        CheckBox cb = new CheckBox();
        RadioButton rb = new RadioButton();
        rb.setToggleGroup(group);
        Button b = new Button("Antwort löschen");

        tfList.add(tf);
        cbList.add(cb);
        rbList.add(rb);
        bList.add(b);

        paneZeichnen();


    }

    @FXML
    protected void changeQuestionTyp(ActionEvent event) {
        paneZeichnen();
    }

    private void paneZeichnen() {
        paneAwnsers.getChildren().clear();
        for (int i = 0; i < tfList.size(); i++) {
            HBox vbox = new HBox();
            vbox.getChildren().add(tfList.get(i));
            if (((RadioButton) choiceTyp.selectedToggleProperty().get()).getText().equals("Single choice"))
                vbox.getChildren().add(rbList.get(i));
            else
                vbox.getChildren().add(cbList.get(i));
            int finalI = i;
            bList.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    tfList.remove(finalI);
                    cbList.remove(finalI);
                    rbList.remove(finalI);
                    bList.remove(finalI);
                    paneZeichnen();
                }
            });
            vbox.getChildren().add(bList.get(i));
            paneAwnsers.getChildren().add(vbox);
        }

        System.out.println(((RadioButton) choiceTyp.selectedToggleProperty().get()).getText());

    }

    @FXML
    protected void questionSave(ActionEvent event) {
        try {
            int questionId = 0;
            int typ = 0;
            stat = new Sql.Connect().connecting();
            if (((RadioButton) choiceTyp.selectedToggleProperty().get()).getText().equals("Single choice"))
                typ = 1;
            else
                typ = 2;

            try {
                stat.executeUpdate("INSERT INTO questions (text, typ) VALUES ('" + questionText.getText() + "', " + typ + ")");
                ResultSet rs = stat.executeQuery("SELECT id FROM questions WHERE text = '" + questionText.getText() + "'");
                while (rs.next())
                    questionId = (rs.getInt("id"));

            } catch (SQLException e) {
                System.out.println("Konnte Frage nicht erstllen.");
            }
            int test = 0;
            for (int i = 0; i < tfList.size(); i++) {
                if (typ == 1)
                    test = (rbList.get(i).isSelected() ? 1 : 0);
                else
                    test = (cbList.get(i).isSelected() ? 1 : 0);
                try {
                    stat.executeUpdate("INSERT  INTO awnsers (question_id, text, true_or_false) VALUES (" + questionId + ", '" + tfList.get(i).getText() + "', " + test + ")");
                } catch (SQLException e) {
                    System.out.println("Antworten konnten nicht in die Datenbank eingefügt werden!!!");
                    e.printStackTrace();
                }
            }

            questionStatus.setText("Frage wurde erfolgreich erstellt");
            questionStatus.setStyle("-fx-font-size: 3px");
        } catch(Exception e) {

        }
    }
}
