package Controllers; /**
 * Sample Skeleton for 'fxml/Login.fxml' Controller Class
 */


import Connectivity.Connectivity;
import Globality.LoginData;
import Model.Login;
import Model.Nurse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController {

    //public static Connectivity conn = new Connectivity();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="logInButton"
    private Button logInButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwdField"
    private PasswordField passwdField; // Value injected by FXMLLoader

    @FXML // fx:id="loginField"
    private TextField loginField; // Value injected by FXMLLoader

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logIn(ActionEvent event) throws IOException {
        String login = loginField.getText();
        String passwd = passwdField.getText();
        Nurse log = LoginData.conn.checkLogin(login,passwd);
        if(log==null) JOptionPane.showMessageDialog(null,"Podano błędny login i/lub hasło");
        else {
            LoginData.imie = log.getFirstName();
            LoginData.nazwisko = log.getLastName();
            LoginData.login = log.getLogin();

            Stage stage = (Stage) logInButton.getScene().getWindow();
            Parent root;
            Stage stageNew = new Stage();
            if(log.getAdmin()) {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminPanel.fxml"));
                stageNew.setTitle("Panel administracyjny");
            }
            else{
                root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScreenPigula.fxml"));
                stageNew.setTitle("Panel pielęgniarki");
            }
            stage.close();
            stageNew.setScene(new Scene(root));
            stageNew.show();
            stage.getScene().getWindow().hide();
        }



    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwdField != null : "fx:id=\"passwdField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
