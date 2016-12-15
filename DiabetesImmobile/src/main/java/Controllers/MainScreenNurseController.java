package Controllers;

import Global.Commons;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class MainScreenNurseController {

    @FXML
    private Label firstAndLastName;

    @FXML
    private Button showPatientDataButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> tabPatients;

    @FXML
    private TableColumn<?, ?> tabPatientsID;

    @FXML
    private TableColumn<?, ?> tabPatientsFirstName;

    @FXML
    private TableColumn<?, ?> tabPatientsLastName;

    @FXML
    void initialize() {

    }

    @FXML
    void selectPatient() {

    }

    @FXML
    void logout() throws IOException {
        Commons.windowControls.logout(logoutButton);
    }

}
