/**
 * Sample Skeleton for 'UserEdit.fxml' Controller Class
 */

package Controllers;

import Global.Commons;
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
        if(Commons.getUserOrNurse()==1) {
            userImie.setText(Commons.getSelectedUser().getFirstName());
            userNazwisko.setText(Commons.getSelectedUser().getLastName());
            userLogin.setText(Commons.getSelectedUser().getLogin());
            userHaslo.setText(Commons.getSelectedUser().getHaslo());
            id = Commons.getSelectedUser().getId();
        }else if(Commons.getUserOrNurse()==2){
            userImie.setText(Commons.getSelectedNurse().getFirstName());
            userNazwisko.setText(Commons.getSelectedNurse().getLastName());
            userLogin.setText(Commons.getSelectedNurse().getLogin());
            userHaslo.setText(Commons.getSelectedNurse().getPassword());
            id = Commons.getSelectedNurse().getId();
        }
    }

    @FXML
    void editUser(ActionEvent event) throws IOException {
        String imie = userImie.getText();
        String nazwisko = userNazwisko.getText();
        String login = userLogin.getText();
        String haslo = userHaslo.getText();
        if(Commons.getUserOrNurse()==1) {
            Commons.conn.editUser(id, imie, nazwisko, login, haslo);
        }else if(Commons.getUserOrNurse()==2){
            Commons.conn.editNurse(id,imie,nazwisko,login,haslo);
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
