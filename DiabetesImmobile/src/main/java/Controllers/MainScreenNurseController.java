package Controllers;

import Global.Commons;
import Model.UsersForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class MainScreenNurseController {

    @FXML
    private Label firstAndLastName;

    @FXML
    private Button showPatientDataButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button refreshPatientsButton;

    @FXML
    private TableView<UsersForTable> tabPatients;

    @FXML
    private TableColumn<UsersForTable, Long> tabPatientsID;

    @FXML
    private TableColumn<UsersForTable, String> tabPatientsFirstName;

    @FXML
    private TableColumn<UsersForTable, String> tabPatientsLastName;

    @FXML
    void initialize() throws IOException {
        firstAndLastName.setText(Commons.getImie()+" "+Commons.getNazwisko());
        refreshPatients();
    }

    @FXML
    void selectPatient() throws IOException {
        UsersForTable user = tabPatients.getSelectionModel().getSelectedItem();
        if(user!=null){
            Commons.setSelectedUser(user);
            Commons.windowControls.newWindow("MainScreenPatient.fxml","Podgląd pacjenta");
        }

    }

    @FXML
    void refreshPatients() throws IOException {
        List<UsersForTable> nurseUsers = Commons.conn.getPatientsOfNurse(Commons.getId());
        ObservableList<UsersForTable> ousers = FXCollections.observableArrayList(nurseUsers);
        tabPatientsID.setCellValueFactory(new PropertyValueFactory<UsersForTable, Long>("id"));
        tabPatientsFirstName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("firstName"));
        tabPatientsLastName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("lastName"));
        tabPatients.setItems(ousers);
    }

    @FXML
    void logout() throws IOException {
        Commons.windowControls.logout(logoutButton);
    }

}
