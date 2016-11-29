/**
 * Sample Skeleton for 'UserEdit.fxml' Controller Class
 */

package Controllers;

import Globality.LoginData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserEditController {

    Long id;

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="userImie"
    private TextField userImie; // Value injected by FXMLLoader

    @FXML // fx:id="userNazwisko"
    private TextField userNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="userLogin"
    private TextField userLogin; // Value injected by FXMLLoader

    @FXML // fx:id="userHaslo"
    private TextField userHaslo; // Value injected by FXMLLoader

    @FXML
    public void initialize() {
        if(LoginData.coDoEdycji==1) {
            userImie.setText(LoginData.selectedUser.getFirstName());
            userNazwisko.setText(LoginData.selectedUser.getLastName());
            userLogin.setText(LoginData.selectedUser.getLogin());
            userHaslo.setText(LoginData.selectedUser.getHaslo());
            id = LoginData.selectedUser.getId();
        }else if(LoginData.coDoEdycji==2){
            userImie.setText(LoginData.selectedNurse.getFirstName());
            userNazwisko.setText(LoginData.selectedNurse.getLastName());
            userLogin.setText(LoginData.selectedNurse.getLogin());
            userHaslo.setText(LoginData.selectedNurse.getPassword());
            id = LoginData.selectedNurse.getId();
        }
    }

    @FXML
    void editUser(ActionEvent event) throws IOException {
        String imie = userImie.getText();
        String nazwisko = userNazwisko.getText();
        String login = userLogin.getText();
        String haslo = userHaslo.getText();
        if(LoginData.coDoEdycji==1) {
            LoginData.conn.editUser(id, imie, nazwisko, login, haslo);
        }else if(LoginData.coDoEdycji==2){
            LoginData.conn.editNurse(id,imie,nazwisko,login,haslo);
        }
        goBack(event);
    }

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) userImie.getScene().getWindow();
        stage.close();
        stage.getScene().getWindow().hide();
    }

}
