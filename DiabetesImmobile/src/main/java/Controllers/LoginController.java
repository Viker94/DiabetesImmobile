/**
 * Sample Skeleton for 'fxml/Login.fxml' Controller Class
 */

package Controllers;

import Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.net.URL;
import java.util.List;
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

        Configuration conf = new Configuration().configure();
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        List logins = session.createQuery("FROM Login").list();
        tx.commit();
        session.close();
        String log = loginField.getText();
        String pass = passwdField.getText();
        boolean zalogowano = false;
        for(int i=0;i<logins.size();i++){
            if((((Login)logins.get(i)).getLogin().equals(log))&&(((Login)logins.get(i)).getPasswd().equals(pass))){
                JOptionPane.showMessageDialog(null,"udało sie zalogować jako id:"+((Login)logins.get(i)).getId());
                zalogowano = true;
                break;
            }
        }
        if(!zalogowano) JOptionPane.showMessageDialog(null,"nie udało sie zalogować");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwdField != null : "fx:id=\"passwdField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
