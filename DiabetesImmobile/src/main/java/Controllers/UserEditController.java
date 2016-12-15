package Controllers;

import Global.Commons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserEditController {

    private Long id;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField userFirstName;

    @FXML
    private TextField userLastName;

    @FXML
    private TextField userLogin;

    @FXML
    private TextField userPassword;

    @FXML
    public void initialize() {
        if(Commons.getUserOrNurse()==1) {
            userFirstName.setText(Commons.getSelectedUser().getFirstName());
            userLastName.setText(Commons.getSelectedUser().getLastName());
            userLogin.setText(Commons.getSelectedUser().getLogin());
            userPassword.setText(Commons.getSelectedUser().getHaslo());
            id = Commons.getSelectedUser().getId();
        }else if(Commons.getUserOrNurse()==2){
            userFirstName.setText(Commons.getSelectedNurse().getFirstName());
            userLastName.setText(Commons.getSelectedNurse().getLastName());
            userLogin.setText(Commons.getSelectedNurse().getLogin());
            userPassword.setText(Commons.getSelectedNurse().getPassword());
            id = Commons.getSelectedNurse().getId();
        }
    }

    @FXML
    void editUser() throws IOException {
        String imie = userFirstName.getText();
        String nazwisko = userLastName.getText();
        String login = userLogin.getText();
        String haslo = userPassword.getText();
        if(Commons.getUserOrNurse()==1) {
            Commons.conn.editUser(id, imie, nazwisko, login, haslo);
        }else if(Commons.getUserOrNurse()==2){
            Commons.conn.editNurse(id,imie,nazwisko,login,haslo);
        }
        goBack();
    }

    @FXML
    void goBack() {
        Commons.windowControls.closeWindow(cancelButton);
    }

}
