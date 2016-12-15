package Controllers;

import Global.Commons;
import Model.Nurse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;


public class LoginController {

    @FXML
    private Button logInButton;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {
        //nothing to do here
    }

    @FXML
    void cancel() {
        System.exit(0);
    }

    @FXML
    void logIn() throws IOException {
        String login = loginField.getText();
        String passwd = passwordField.getText();
        Nurse log = Commons.conn.checkLogin(login,passwd);
        if(log==null) JOptionPane.showMessageDialog(null,"Podano błędny login i/lub hasło");
        else {
            Commons.setImie(log.getFirstName());
            Commons.setNazwisko(log.getLastName());
            Commons.setLogin(log.getLogin());

            if(log.getAdmin()) {
                Commons.windowControls.replaceWindow("AdminPanel.fxml","Panel administracyjny",logInButton);
            }
            else{
                Commons.windowControls.replaceWindow("MainScreenNurse.fxml","Panel pielęgniarki",logInButton);
            }
        }
    }
}
