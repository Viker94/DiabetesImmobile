package Controllers; /**
 * Sample Skeleton for 'fxml/Login.fxml' Controller Class
 */



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

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
    void logIn(ActionEvent event) {
        String login = loginField.getText();
        String passwd = passwdField.getText();
        //tutaj spradź login i hasło
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwdField != null : "fx:id=\"passwdField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
