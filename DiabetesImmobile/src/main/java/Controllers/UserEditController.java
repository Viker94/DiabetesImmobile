package Controllers;

import Global.Commons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
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
        if(imie.trim().equals("") || nazwisko.trim().equals("") || login.trim().equals("") || haslo.trim().equals("")){
            JOptionPane.showMessageDialog(null,"Wartości nie mogą być puste","Błąd",JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (Commons.getUserOrNurse() == 1) {
                if (Commons.conn.checkUserAvailibility(login.trim(), id)) {
                    Commons.conn.editUser(id, imie.trim(), nazwisko.trim(), login.trim(), haslo.trim());
                    goBack();
                } else
                    JOptionPane.showMessageDialog(null, "Podany login jest już zajęty", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else if (Commons.getUserOrNurse() == 2) {
                if (Commons.conn.checkNurseAvailibility(login.trim(), id)) {
                    Commons.conn.editNurse(id, imie.trim(), nazwisko.trim(), login.trim(), haslo.trim());
                    goBack();
                } else
                    JOptionPane.showMessageDialog(null, "Podany login jest już zajęty", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    void goBack() {
        Commons.windowControls.closeWindow(cancelButton);
    }

}
