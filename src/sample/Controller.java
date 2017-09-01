package sample;


import Sql.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    @FXML
    Button loginButton;
    @FXML
    TextField tfLoginName;
    @FXML
    TextField tfLoginPassword;

    private Stage stage;

    @FXML
    private Pane testPane;

    @FXML
    protected void btloginButton (ActionEvent event) throws IOException, SQLException {
        if (new Login().loginCheck(tfLoginName.getText(), tfLoginPassword.getText())) {
            Parent root = FXMLLoader.load(getClass().getResource("../MainFrame/MainFrame.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setX(0);
            stage.setY(0);
            stage.setScene(new Scene(root));

        }
    }

    @FXML
    protected void testButton (ActionEvent event) throws IOException {

    }

}
