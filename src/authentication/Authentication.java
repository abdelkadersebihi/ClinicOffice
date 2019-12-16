package authentication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Main;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Authentication implements Initializable {
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordTextField;
    @FXML
    private JFXButton loginButton,resetButton;
    @FXML
    private Label errorLabel,titleText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)){ login(); }
            }
        });
        usernameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)){ login(); }
            }
        });
        errorLabel.setVisible(false);
    }

    public void handleClick(ActionEvent event)throws Exception {
        if(event.getSource().equals(loginButton)){
            login();
        }else if (event.getSource().equals(resetButton)){
            usernameTextField.setText(null);
            passwordTextField.setText(null);
        }
    }

    void login() {
        if (!usernameTextField.getText().equals(DBController.getUsername()) || !passwordTextField.getText().equals(DBController.getPassword())) {
            errorLabel.setVisible(true);
        }else {
            errorLabel.setVisible(false);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../homepage/HomePage.fxml"));
                Main.mainStage.hide();
                Main.mainStage.close();
                Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
                Main.mainStage.setScene(new Scene(root, res.width - 80, res.height - 90));
                Main.mainStage.setAlwaysOnTop(false);
                Main.mainStage.setResizable(true);
                Main.mainStage.show();
            }catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}





