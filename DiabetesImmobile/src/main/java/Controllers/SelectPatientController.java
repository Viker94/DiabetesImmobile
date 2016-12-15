package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SelectPatientController {

    @FXML
    private TableView<?> tabPatients;

    @FXML
    private TableColumn<?, ?> tabPatientsID;

    @FXML
    private TableColumn<?, ?> tabPatientsFirstName;

    @FXML
    private TableColumn<?, ?> tabPatientsLastName;

    @FXML
    private TableColumn<?, ?> tabPatientsLogin;

    @FXML
    private TableColumn<?, ?> tabPatientsNumberOfNurses;

    @FXML
    private Button selectButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button refreshButton;

    @FXML
    void initialize() {
        refreshTable();
    }

    @FXML
    void cancel() {

    }

    @FXML
    void select() {

    }

    @FXML
    void refreshTable(){

    }

}
